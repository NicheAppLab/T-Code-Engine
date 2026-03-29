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
    // Convert "abcdef" -> List(('a','b'), ('c','d'), ('e','f'))
    val pairs: List[(Int, Int)] = str.map(this.getStroke(_)).flatten.grouped(2)
      .collect { case s if s.length == 2 => (s(0), s(1)) }
      .toList

    loop(pairs, Nil).mkString
  }

  @scala.annotation.tailrec
  private def loop(remaining: List[(Int, Int)], acc: List[Char]): List[Char] = {
    remaining match
    case Nil => acc
    case (26, 23) :: tail => {
      val (res, nextTail) = resolveComposite(tail)
      // Add to accumulator only if the result is Some(char)
      loop(nextTail, acc ++ res.toList)
    }
    case (c1, c2) :: tail => {
      val res = Strokes.get(c1,c2)
      loop(tail, acc.appended(res))
    }
  }
  // Resolves the next two "logical" results to feed into composite
  private def resolveComposite(input: List[(Int, Int)]): (Option[Char], List[(Int, Int)]) = {
    val (first, tail1) = resolveNext(input)
    val (second, tail2) = resolveNext(tail1)
    // Use for-comprehension to handle the nested Options
    val combined =
      for a <- first
          b <- second
          res <- Combinator.composite(a, b)
      yield res
    (combined, tail2)
  }

  // Helper to get exactly one result (could be a nested jf)
  private def resolveNext(input: List[(Int, Int)]): (Option[Char], List[(Int, Int)]) = {
    input match {
      case Nil => (None, Nil)
      case (26, 23) :: tail => resolveComposite(tail)
      case (c1, c2) :: tail   => (Some(Strokes.get(c1,c2)), tail)
    }
  }

  def mixedConvert(str: String): Array[String] = {
    MixedConverter.get(str)
  }
}
