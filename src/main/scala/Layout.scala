package io.github.nicheapplab.tcodeengine

abstract trait Layout{
  val keyseq: Seq[Char]
  def getStroke(c: Char): Option[Int] = {
    if (keyseq.contains(c)) {
      Some (keyseq.indexWhere(_ == c))
    } else {
      None
    }
  }
  def getKey(i: Int): Option[Char] = {
    if (i<40){
      Some(keyseq(i))
    } else None
  }
}

/** Provides validation of Qwerty keys stroke for T-Code */
trait QwertyLayout extends Layout{
  val keyseq = "1234567890qwertyuiopasdfghjkl;zxcvbnm,./"
}
/** Provides validation of Dvorak key stroke for T-Code */
trait DvorakLayout extends Layout{
  val keyseq = "1234567890\',.pyfgcrlaoeuidhtns;qjkxbmwvz"
}
