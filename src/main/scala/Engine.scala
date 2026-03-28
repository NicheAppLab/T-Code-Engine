package io.github.nicheapplab.t_codeengine
/** Generates Japanese characters from key cordinates or Qwerty key chars with T-Code
  *
  * The main class to use is [[io.github.nicheapplab.t_codeengine.Engine]], as so
  * {{{
  * scala> import com.nicheapplab.t_codeengine._
  * scala> val engine = new Engine with QwertyLayout
  * scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
  * val res0: String = "で、ので、では、を、のを、とを"
  * }}}
  * Alternatively, a predefined [[DvorakLayout]] can be specified.
  */
class Engine {
  this: Layout =>

  def convert(str: String): String = {
    val strokes:Array[Char] = str.map(c => this.getStroke(c)).flatten.toArray
    strokes.sliding(2, 2).map(
      pair => Strokes.project("" + pair(0) + pair(1)).mkString
    ).mkString
  }
}
