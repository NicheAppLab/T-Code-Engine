package io.github.nicheapplab.t_code_engine

import scala.io.Source
import scala.util.Using

trait Combinator{ this: CombinatorDictionary =>
  def composite(a: Char, b: Char): Option[Char] = {
    findLine(a, b) match{
      case Some(line) => Some((line.charAt(0)))
      case None => None
    }
  }
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
    val (second, tail2) = resolveNext(tail1)
    // Use for-comprehension to handle the nested Options
    val combined =
      for a <- first
          b <- second
          res <- composite(a, b)
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
}

trait CombinatorDictionary{
  def findLine(a: Char, b: Char):Option[String] = {
    Using.resource(Source.fromResource("bushu.rev")){source =>
      source.getLines().find(line => line.contains(""+a+b) || line.contains(""+b+a))
    }
  }
}
