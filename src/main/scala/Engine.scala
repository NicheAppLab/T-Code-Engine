package io.github.nicheapplab.t_code_engine
/** Generates Japanese characters from key cordinates or Qwerty key chars with T-Code
  *
  * The main class to use is [[io.github.nicheapplab.t_code_engine.Engine]], as so
  * {{{
  * scala> import com.nicheapplab.t_code_engine._
  * scala> val engine = new Engine with QwertyLayout
  * scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
  * val res0: String = "で、ので、では、を、のを、とを"
  * }}}
  * Alternatively, a predefined [[DvorakLayout]] can be specified.
  */
class Engine {
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
