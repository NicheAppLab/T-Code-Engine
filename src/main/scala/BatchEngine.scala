package io.github.nicheapplab.tcodeengine
/** Generates Japanese characters from key strokes defined by T-Code
  *
  * BatchEngine provides batched conversion into Japanese. Prefix key strokes
  * should be handled outside of the library. The other engine,
  * [[io.github.nicheapplab.tcodeengine.InteractiveEngine]] will handle prefix
  * key strokes and inflex specification.
  * {{{
  * scala> import io.github.nicheapplab.tcodeengine._
  * scala> val engine = new BatchEngine with QwertyLayout
  * scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
  * val res0: String = "で、ので、では、を、のを、とを"
  * }}}
  * Alternatively, a predefined [[DvorakLayout]] can be specified.
  */
class BatchEngine {
  this: Layout =>

  val mixed = new MixedConverter with MixedConverterDictionary
  val combi = new Combinator with CombinatorDictionary

  def convert(str: String): String = {
    // Convert "abcdef" -> List(('a','b'), ('c','d'), ('e','f'))
    val pairs: List[(Int, Int)] = str.map(this.getStroke(_)).flatten.grouped(2)
      .collect { case s if s.length == 2 => (s(0), s(1)) }
      .toList

    compositeLoop(pairs, Nil).mkString
  }
  def compositeLoop(remaining: List[(Int, Int)], acc: List[Char]): List[Char] = combi.loop(remaining, acc)
  def mixedConvert(str: String): Array[String] = {
    mixed.convert(str)
  }
  def mixedConvert(str: String, inflex: String): Array[String] = {
    mixed.convert(str, inflex)
  }
}
