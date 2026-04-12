package io.github.nicheapplab.tcodeengine

trait Combinator{ this: CombinatorDictionary =>
  def composite(a: Char, b: Char): Option[Char] = find(a, b)

  import scala.util.control.TailCalls._

  @scala.annotation.tailrec
  final def loop(remaining: List[(Int, Int)], acc: List[Char]): List[Char] = {
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
    first match{
      case None => (None, input)
      case Some(c1) => {
        find(c1, ' ') match
        case None => {
          val (second, tail2) = resolveNext(tail1)
          // Use for-comprehension to handle the nested Options
          val combined =
            for {
              a <- first
              b <- second
              res <- composite(a, b)
            } yield res
          (combined, tail2)
        }
        case Some(c2) => (Some(c2), tail1)
      }
    }
  }


  // Helper to get exactly one result (could be a nested jf)
  private def resolveNext(input: List[(Int, Int)]): (Option[Char], List[(Int, Int)]) = {
    input match {
      case Nil => (None, Nil)
      case (26, 23) :: tail => resolveComposite(tail)
      case (c1, c2) :: tail   => (Some(Strokes.get(c1,c2)), tail)
    }
  }
}


