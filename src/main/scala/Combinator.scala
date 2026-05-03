package io.github.nicheapplab.tcodeengine

import scala.collection.mutable.ListBuffer

trait Combinator (val strokes: Strokes) { this: CombinatorDictionary =>
  def composite(a: Char, b: Char): Option[Char] = {
    findComposition(a, b) orElse{
      // subtract
      // e.g. 頭 - 豆 = 頁
      findParts(a).collectFirst {
        case (x, y) if x == b => y
        case (x, y) if y == b => x
      }
    } orElse {
      // extract common parts
      // e.g. 頭 & 題 = 頁
      val parts_a = findParts(a).flatMap( pair => Seq(pair._1, pair._2) ).toSet
      val parts_b = findParts(b).flatMap( pair => Seq(pair._1, pair._2) ).toSet
      (parts_a intersect parts_b).headOption
    } orElse {
      // parts with full
      // e.g. 頭 + 工 or 工 + 頭
      val from_parts_of_a = findParts(a).flatMap(pair => composite(pair._1, b) orElse composite(pair._2, b))
      val from_parts_of_b = findParts(b).flatMap(pair => composite(a, pair._1) orElse composite(a, pair._2))
      (from_parts_of_a ++ from_parts_of_b).headOption
    }
  }
  def resolveComposition(focused: Char, remaining: ListBuffer[Char]): Char = {
    val compositionLevel = remaining.count(_ == '▲')
    if (compositionLevel <= 0 || remaining.isEmpty){
      focused
    } else if (remaining.last != '▲') {
      val last: Char = remaining.last
      composite(last, focused) match {
        case Some(res) => {
          remaining.dropRightInPlace(1) // drop last letter
          remaining.remove(remaining.lastIndexOf('▲')) // drop ▲
          resolveComposition(res, remaining)
        }
        case None => ' '
      }
    } else {
      composite(focused, ' ') match{
        case Some(res) => {
          remaining.dropRightInPlace(1)
          resolveComposition(res, remaining)
        }
        case None => {
          focused
        }
      }
    }
  }

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
      val res = strokes.getChar(c1,c2)
      loop(tail, acc.appended(res))
    }
  }
  // Resolves the next two "logical" results to feed into composite
  private def resolveComposite(input: List[(Int, Int)]): (Option[Char], List[(Int, Int)]) = {
    val (first, tail1) = resolveNext(input)
    first match{
      case None => (None, input)
      case Some(c1) => {
        findComposition(c1, ' ') match
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
      case (c1, c2) :: tail   => (Some(strokes.getChar(c1,c2)), tail)
    }
  }
}


