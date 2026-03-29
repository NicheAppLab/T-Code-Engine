package io.github.nicheapplab.t_codeengine

import scala.io.Source
import scala.util.Using

object Combinator{
  private def findLine(a: Char, b: Char):Option[String] = {
    Using.resource(Source.fromResource("bushu.rev")){source =>
      source.getLines().find(line => line.contains(""+a+b) || line.contains(""+b+a))
    }
  }
  def composite(a: Char, b: Char): Option[Char] = {
    findLine(a, b) match{
      case Some(line) => Some((line.charAt(0)))
      case None => None
    }
  }
}
