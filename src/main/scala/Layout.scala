package io.github.nicheapplab.tcodeengine

abstract trait Layout{
  def getStroke(c: Char): Option[Int]
}

/** Provides validation of Qwerty keys stroke for T-Code */
trait QwertyLayout extends Layout{
  /**
    * Returns a char that can be part of the key stroke of T-Code
    *
    * @param c a character typed with a Qwerty keyboard layout
    * @return an Option of a Int which indicates key number if valid
    */
  def getStroke(c: Char): Option[Int] = {
    if (QwertyLayout.keyseq.contains(c)) {
      Some (QwertyLayout.keyseq.indexWhere(_ == c))
    } else {
      None
    }
  }
}
/** Defines the keyboard layout of QwertyLayout */
object QwertyLayout{
  val keyseq = "1234567890qwertyuiopasdfghjkl;zxcvbnm,./"
}

/** Provides validation of Dvorak key stroke for T-Code */
trait DvorakLayout extends Layout{
  /**
    * Returns a key number that can be part of the key stroke of T-Code
    *
    * @param c: A character typed with a Dvorak keyboard layout
    * @return an Option of a Int which indicates key number if valid
    */
  def getStroke(c: Char): Option[Int] = {
      if (DvorakLayout.keyseq.contains(c)){
        Some(DvorakLayout.keyseq.indexWhere(_ == c) )
      } else {
        None
      }
    }
}
/** Defines the keyboard layout of DvorakLayout */
object DvorakLayout{
  val keyseq = "1234567890\',.pyfgcrlaoeuidhtns;qjkxbmwvz"
}
