package io.github.nicheapplab.t_codeengine

import scala.io.Source

object Combinator{
  def findLine(a: Char, b: Char):Option[String] = {
    val lines = Source.fromResource("bushu.rev").getLines()
    try {
      lines.find(line => line.contains(""+a+b))
    } finally {
      lines match{
        case bufferedSource: Source => bufferedSource.close()
        case _ =>
      }
    }
  }
  def composite(a: Char, b: Char): Option[Char] = {
    findLine(a, b) match{
      case Some(line) => Some((line.charAt(0)))
      case None => None
    }
  }
}
