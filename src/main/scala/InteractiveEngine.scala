package io.github.nicheapplab.tcodeengine

import scala.collection.mutable.ListBuffer
import scala.compiletime.ops.boolean

/** Generates Japanese characters from key cordinates or Qwerty key chars with
  * T-Code
  *
  * InteractiveEngine provides batched conversion into Japanese. Prefix key
  * strokes should be included in the input key strokes. In the following
  * example, "fj" indicates entering Mixed Conversion mode (as "△", internally).
  * In this mode, user can feed *kanji* and *hiragana* mixed input and generates
  * conversion candidates through `convert()` function.
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  * scala> val engine = new InteractiveEngine with QwertyLayout
  * scala> "fjyijstt".foreach(ie.put(_))
  * scala> ie.inflexRight()
  * scala> ie.convert()
  * scala> ie.selectCandidate(0)
  * scala> ie.commit()
  * val res0: String = "記者"
  * }}} To use *kanji* composition, type the prefix "jf" to enter Composition
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
  * scala> val ie = new InteractiveEngine with QwertyLayout
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
  * Alternatively, a predefined [[DvorakLayout]] can be specified.
  */
class InteractiveEngine { this: Layout =>
  private val mixed = new MixedConverter with MixedConverterDictionary
  private val combi = new Combinator with CombinatorDictionary
  private val _outputBuffer = ListBuffer[Char]()
  private val _buffer = ListBuffer[Char]()
  private val _candidates = ListBuffer[String]()
  private var inflexPos: Option[Int] = None
  private var mixedMode = false
  private var compositionLevel = 0
  private var _lastChar: Option[Int] = None

  def outputBuffer = _outputBuffer
  def buffer = _buffer
  def candidates = _candidates
  def lastChar = _lastChar
  def lastCharAsKey = lastChar.flatMap(getKey(_))

  def put(c: Char): Unit = if(candidates.isEmpty) _put(c)
  private def _put(c: Char): Unit = {
    (mixedMode, compositionLevel, _lastChar, this.getStroke(c)) match {
      case (_, _, _, None)   => {}
      case (_, _, None, oc2) => { _lastChar = oc2 }
      case (_, _, Some(26), Some(23)) => {
        _lastChar = None
        compositionLevel += 1
        buffer += '▲'
      }
      case (false, 0, Some(23), Some(26)) => {
        _lastChar = None
        mixedMode = true
        buffer += '△'
      }
      case (false, 0, Some(c1), Some(c2)) => {
        _lastChar = None
        outputBuffer += Strokes.get(c1, c2)
      }
      case (true, 0, Some(23), Some(26)) => {
        _lastChar = None
      }
      case (true, 0, Some(c1), Some(c2)) => {
        _lastChar = None
        buffer += Strokes.get(c1, c2)
      }
      case (true, _, Some(23), Some(26)) => { _lastChar = None }
      case (_, lv, Some(c1), Some(c2)) if lv > 0 => {
        if (buffer.size == 0) {} else if (buffer.last == '▲') {
          _lastChar = None
          val c = Strokes.get(c1, c2)
          combi.composite(c, ' ') match{
            case Some(res) => {
              buffer.dropRightInPlace(1)
              buffer.append(res)
              compositionLevel -= 1
              if(mixedMode == false && compositionLevel == 0){
                outputBuffer ++= buffer
                buffer.clear()
              }
            }
            case None => {
              buffer.append(c)
            }
          }
        } else {
          val char1 = buffer.last
          _lastChar = None
          var char2 = Strokes.get(c1, c2)
          combi.composite(char1, char2) match {
            case None => {
              buffer.dropRightInPlace(1)
            }
            case Some(res) => {
              buffer.dropRightInPlace(1)
              compositionLevel -= 1
              if(compositionLevel > 0 && buffer.last != '▲'){
                val part1 = buffer.last
                combi.composite(part1, res) match {
                  case None => {}
                  case Some(res2) => {
                    compositionLevel -= 1
                    outputBuffer += res2
                    buffer.dropRightInPlace(2)
                  }
                }
              }
              val pos = buffer.lastIndexOf('▲')
              buffer.remove(pos)
              buffer.append(res)
              if (mixedMode == false && compositionLevel == 0) {
                outputBuffer ++= buffer
                buffer.clear()
              }
            }
          }
        }
      }
      case (_, _, Some(_), Some(_)) => {}
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
