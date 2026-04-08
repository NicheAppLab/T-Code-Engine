package io.github.nicheapplab.tcodeengine

import scala.io.Source
import scala.util.Using
/** Provides the map of Qwerty two-keys-stroke to Japanese character */
object Strokes {
  private val dictionary: Array[Array[Char]] = Using.resource(Source.fromResource("tcode_tbl.txt")){ source =>
    source.getLines()
      .map(_.toArray)
      .toArray
  }

  def get(first: Int, second: Int):Char = dictionary(second)(first)
}
