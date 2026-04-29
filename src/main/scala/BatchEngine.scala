package io.github.nicheapplab.tcodeengine

/**
  * BatchEngine provides batched conversion into Japanese. Prefix key strokes
  * should be handled outside of the library. It has two subclasses:
  * - [[ArchivedBatchEngine]] uses dictionary files from a zip archive file
  * - [[SQLiteBatchEngine]] uses SQLite database which is created from the zip
  * archive file
  *
  * Both of them uses [[Layout]] type for its self annotation.
  * Currently [[QwertyLayout]] and [[DvorakLayout]] are supported.
  * */
abstract trait BatchEngine { this: Layout & Strokes =>
  val mixed: MixedConverter
  val combi: Combinator

  def convert(str: String): String = {
    // Convert "abcdef" -> List(('a','b'), ('c','d'), ('e','f'))
    val pairs: List[(Int, Int)] = str.map(this.getStroke(_)).flatten.grouped(2)
      .collect { case s if s.length == 2 => (s(0), s(1)) }
      .toList

    compositeLoop(pairs).mkString
  }
  private def compositeLoop(remaining: List[(Int, Int)]): List[Char] = combi.loop(remaining, Nil)
  def mixedConvert(str: String): Array[String] = {
    mixed.convert(str)
  }
  def mixedConvert(str: String, inflex: String): Array[String] = {
    mixed.convert(str, inflex)
  }
}
/**
  * Converts key strokes into Japanese characters by using zip archived dictionary file.
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  * scala> val engine = new ArchivedBatchEngine with QwertyLayout
  * scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
  * val res0: String = "で、ので、では、を、のを、とを"
  * }}}
  * */
class ArchivedBatchEngine extends BatchEngine with ArchivedStrokes { this: Layout =>
  val mixed = new MixedConverter with ArchivedMixedConverterDictionary
  val combi = new Combinator(this) with ArchivedCombinatorDictionary
}

/**
  * Converts key strokes into Japanese characters by using dictionaries stored
  * in SQLite database files. These files locates in the specified locations and
  * are created from zip archivve file for the first time.
  *
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  * scala> val (tcode_tbl_path, mazegaki_path, bushu_path) = ...
  * scala> val engine = new SQLiteBatchEngine(tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout
  * scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
  * val res0: String = "で、ので、では、を、のを、とを"
  * }}}
  * */
class SQLiteBatchEngine (tcode_tbl_path: String,
                   mazegaki_path: String,
                   bushu_path: String)
extends BatchEngine with SQLiteStrokes(tcode_tbl_path){ this: Layout =>

  val mixed = new MixedConverter with SQLiteMixedConverterDictionary(mazegaki_path)
  val combi = new Combinator(this) with SQLiteCombinatorDictionary(bushu_path)

}
