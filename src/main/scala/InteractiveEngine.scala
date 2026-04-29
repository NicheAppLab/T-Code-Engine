package io.github.nicheapplab.tcodeengine

import scala.collection.mutable.ListBuffer
import scala.compiletime.ops.boolean

/** Abstract class of InteractiveEngine, which generates Japanese characters step by step, interacting the user with its method calls.
  *
  * It has two subclasses:
  * - [[ArchivedInteractiveEngine]] uses dictionary files from a zip archive file
  * - [[SQLiteInteractiveEngine]] uses SQLite database which is created from the
  * zip archive file
  *
  * Both of them uses [[Layout]] type for its self annotation.
  * Currently [[QwertyLayout]] and [[DvorakLayout]] are supported.
  *
  * Prefix key
  * strokes should be included in the input key strokes. In the following
  * example, "fj" indicates entering Mixed Conversion mode (as "△", internally).
  * In this mode, user can feed *kanji* and *hiragana* mixed input and generates
  * conversion candidates through `convert()` function.
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  * scala> val ie = new ArchivedInteractiveEngine with QwertyLayout
  * scala> "fjyijstt".foreach(ie.put(_))
  * scala> ie.inflexRight()
  * scala> ie.convert()
  * scala> ie.selectCandidate(0)
  * scala> ie.commit()
  * val res0: String = "記者"
  * }}}
  *
  * To use *kanji* composition, type the prefix "jf" to enter Composition
  * mode (as "▲", internally). Composition mode can be nested as well as inside
  * of Mixed Conversion mode. In the following example, after typing "fjjfpw",
  * its internal buffer will be "△▲木". Followed by ".v" (corresponds to "目"),
  * "▲木" will be popped and composition consumes them, generating "相". The
  * internal buffer will be "△相". Then typing ".d" ("つ") and "dt"("ぐ") will
  * make the internal buffer to "△相つぐ". `InteractiveEngine.inflexLeft()`
  * makes it to "△相つ|ぐ", which indicates "ぐ" is the inflex for the
  * conversion. `InteractiveEngine.convert()` will clear the buffer and put the
  * candidates into `InteractiveEngine.candidates` buffer. The user then can
  * choose the desired candidate with its index of the buffer, such as
  * `ie.selectCandidates(3)`. This will clear the `candidates` buffer and put
  * the converted text "相次ぐ" into `outputBuffer`. To extract text from
  * `outputBuffer`, call `InteractiveEngine.commit()`.
  * {{{
  * scala> "fjjfpw.v.ddt".foreach(ie.put(_))
  * scala> ie.inflexLeft()
  * scala> ie.convert()
  * scala> ie.selectCandidates(0)
  * scala> "fjjeux".foreach(ie.put(_))
  * scala> ie.inflexRight()
  * scala> ie.convert()
  * scala> ie.selectCandidate(3)
  * scala> "kgjwjc".foreach(ie.put(_))
  * scala> ie.commit()
  * val res0: String = "相次ぐ火事により"
  * }}}
  */
abstract class InteractiveEngine extends Strokes { this: Layout =>
  val mixed: MixedConverter
  val combi: Combinator
  private val _outputBuffer = ListBuffer[Char]()
  private val _buffer = ListBuffer[Char]()
  private val _candidates = ListBuffer[String]()
  private var inflexPos: Option[Int] = None
  private var mixedMode = false
  private var compositionLevel = 0
  private val KEY_F = 23
  private val KEY_J = 26
  private var _lastChar: Option[Int] = None

  def outputBuffer = _outputBuffer
  def buffer = _buffer
  def candidates = _candidates
  def lastChar = _lastChar
  def lastCharAsKey = lastChar.flatMap(getKey(_))

  def put(c: Char): Unit = if (candidates.isEmpty) _put(c)

  private def _put(c: Char): Unit = {
    this.getStroke(c) match {
      case None => // Ignore invalid keys
      case Some(current) =>
        _lastChar match {
          case None =>
            _lastChar = Some(current)
          case Some(last) =>
            _lastChar = None
            processStroke(last, current)
        }
    }
  }

  private def processStroke(c1: Int, c2: Int): Unit = {
    if (c1 == KEY_J && c2 == KEY_F) {
      enterCompositionMode()
    } else if (c1 == KEY_F && c2 == KEY_J && !mixedMode && compositionLevel == 0) {
      enterMixedMode()
    } else if (c1 == KEY_F && c2 == KEY_J && mixedMode) {
      // Ignore "fj" (Mixed mode prefix) if already in mixed mode
    } else if (compositionLevel > 0) {
      handleCompositionInput(c1, c2)
    } else if (mixedMode) {
      buffer += getChar(c1, c2)
    } else {
      outputBuffer += getChar(c1, c2)
    }
  }

  private def enterCompositionMode(): Unit = {
    compositionLevel += 1
    buffer += '▲'
  }

  private def enterMixedMode(): Unit = {
    mixedMode = true
    buffer += '△'
  }

  private def handleCompositionInput(c1: Int, c2: Int): Unit = {
    val char2 = getChar(c1, c2)
    if (buffer.nonEmpty && buffer.last == '▲') {
      combi.composite(char2, ' ') match {
        case Some(res) =>
          buffer.dropRightInPlace(1)
          buffer.append(res)
          compositionLevel -= 1
        case None =>
          buffer.append(char2)
      }
    } else if (buffer.nonEmpty) {
      val char1 = buffer.last
      combi.composite(char1, char2) match {
        case Some(res) =>
          buffer.dropRightInPlace(1)
          compositionLevel -= 1

          if (compositionLevel > 0 && buffer.last != '▲') {
            val part1 = buffer.last
            combi.composite(part1, res) match {
              case Some(res2) =>
                compositionLevel -= 1
                outputBuffer += res2
                buffer.dropRightInPlace(2)
              case None =>
            }
          }

          val pos = buffer.lastIndexOf('▲')
          if (pos != -1) {
            buffer.remove(pos)
          }
          buffer.append(res)

        case None =>
          buffer.dropRightInPlace(1)
      }
    }

    if (!mixedMode && compositionLevel == 0) {
      outputBuffer ++= buffer
      buffer.clear()
    }
  }

  def inflexLeft() = inflexPos match {
    case None if buffer.size == 0 => {}
    case None =>
      inflexPos = Some(buffer.length - 1)
      buffer.insert(buffer.length - 1, '|')
    case Some(n) =>
      if (n == 0) {
      } else {
        buffer.remove(n)
        inflexPos = Some(n - 1)
        buffer.insert(n - 1, '|')
      }
  }
  def inflexRight() = inflexPos match {
    case None if buffer.size == 0 => {}
    case None =>
      inflexPos = Some(buffer.size)
      buffer.insert(buffer.size, '|')
    case Some(n) =>
      if (n == buffer.size - 1){
      } else {
        buffer.remove(n)
        inflexPos = Some(n + 1)
        buffer.insert(n + 1, '|')
      }
  }

  def reset() = if (candidates.isEmpty) _reset()
  private def _reset() = {
    outputBuffer.clear()
    buffer.clear()
    candidates.clear()
    mixedMode = false
    inflexPos = None
    _lastChar = None
  }
  def convert() = if (candidates.isEmpty) _convert()
  private def _convert() = {
    buffer.remove(buffer.indexOf('△'))
    inflexPos match {
      case None =>
        candidates ++= mixed.convert(buffer.mkString)
      case Some(n) =>
        if (buffer.head == '|') {
          candidates += buffer.slice(1, buffer.size).mkString
        } else if (buffer.last == '|') {
          candidates ++= mixed.convert(
            buffer.slice(0, buffer.size - 1).mkString
          )
        } else {
          val index = buffer.indexOf('|')
          val (target, bar_inflex) = buffer.splitAt(index)
          val inflex = bar_inflex.slice(1, bar_inflex.size)
          candidates ++= mixed.convert(target.mkString, inflex.mkString)
        }
    }
    buffer.clear()
    inflexPos = None
    mixedMode = false
  }
  def selectCandidate(n: Int) = {
    if(!candidates.isEmpty)
      outputBuffer ++= candidates(n)
    candidates.clear()
  }

  def commit(): String = {
    val res = outputBuffer ++ buffer
    reset()
    res.mkString
  }

  def backspace(): Boolean = if (candidates.isEmpty){
    _backspace()
  } else {
    true
  }
  private def _backspace(): Boolean = {
    (lastChar, buffer.isEmpty, outputBuffer.isEmpty) match{
      case (Some(_), _, _) =>
        _lastChar = None
        return true
      case (None, true, true) =>
        return false
      case (None, false, _) =>
        if (buffer.last == '▲'){
          compositionLevel -= 1
        } else if(buffer.last == '△') {
          mixedMode = false
        } else if(buffer.last == '|'){
          inflexPos = None
        }
        _buffer.dropRightInPlace(1)
        return true
      case (None, true, false) =>
        _outputBuffer.dropRightInPlace(1)
        return true
    }
  }
}

class ArchivedInteractiveEngine extends InteractiveEngine with ArchivedStrokes { this: Layout =>
  val mixed = new MixedConverter with ArchivedMixedConverterDictionary
  val combi = new Combinator(this) with ArchivedCombinatorDictionary
}

/**
  * To use SQLite databases as dictionaries, it must be provided with file path for the database files.
  * If any of the database files don't exist, the missing database file will be created from zip archived dictionary.
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  *
  * scala> val tcode_tbl_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/tcode_tbl.db"
  * scala> val mazegaki_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/mazegaki.db"
  * scala> val bushu_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/bushu.db"
   
  * scala> val ie = new SQLiteInteractiveEngine(tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout
  * scala> "fjyijstt".foreach(ie.put(_))
  * scala> ie.inflexRight()
  * scala> ie.convert()
  * scala> ie.selectCandidate(0)
  * scala> ie.commit()
  * val res0: String = "記者"
  * }}}
  * */
class SQLiteInteractiveEngine(
  tcode_tbl_path: String,
  mazegaki_path: String,
  bushu_path: String
) extends InteractiveEngine with SQLiteStrokes(tcode_tbl_path) { this: Layout =>
  val mixed = new MixedConverter with SQLiteMixedConverterDictionary(mazegaki_path)
  val combi = new Combinator(this) with SQLiteCombinatorDictionary(bushu_path)
}
