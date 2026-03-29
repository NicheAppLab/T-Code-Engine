package io.github.nicheapplab.t_codeengine

import scala.io.Source
/** Provides the map of Qwerty two-keys-stroke to Japanese character */
object Strokes {
  val dictionary: Array[Array[Char]] = Source.fromResource("tcode_tbl.txt")
  .getLines()
  .map(_.toArray)
  .toArray

  def get(first: Int, second: Int):Char = dictionary(second)(first)
}
