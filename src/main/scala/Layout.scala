package io.github.nicheapplab.t_codeengine

abstract trait Layout{
  def getStroke(c: Char): Option[Char]
}

/** Provides validation of Qwerty keys stroke for T-Code */
trait QwertyLayout extends Layout{
  import QwertyLayout._

  /**
    * Returns a char that can be part of the key stroke of T-Code
    *
    * @param c a character typed with a Qwerty keyboard layout
    * @return an Option of a char can be part of the key stroke of T-Code if valid, otherwise None
    */
  def getStroke(c: Char): Option[Char] = {
    if (left.contains(c) || right.contains(c)){
      Some(c)
    } else {
      None
    }
  }
}
/** Defines the keyboard layout of QwertyLayout */
object QwertyLayout{
  val left = "12345qwertasdfgzxcvb"
  val right = "67890yuiophjkl;nm,./"

  /**
    * Given a internal Int presentation of a stroke, find the key when in a Qwerty keyboard.
    * This is used for keyboard layouts other than Qwerty
    * @param stroke Qwerty presentation of a key stroke
    * @return Qwerty key representation of the stroke if valid. Otherwise None.
    */
  def getQwertyChar(stroke: Int): Option[Char] = {
    if(0 <= stroke && stroke <= 39){
      Some( (left+right).charAt(stroke) )
    } else None
  }
}

/** Provides validation of Dvorak key stroke for T-Code */
trait DvorakLayout extends Layout{
  import DvorakLayout._
  /**
    * Returns a char that can be part of the key stroke of T-Code
    *
    * @param c: A character typed with a Dvorak keyboard layout
    * @return an Option of a char in Qwerty keyboard layout presentation after validation.
    * It can be part of the key stroke of T-Code if valid, otherwise None
    */
  def getStroke(c: Char): Option[Char] = {
    val k: Option[Int] = if (left.contains(c)) {
      Some(left.indexWhere(_ == c))
    } else if (right.contains(c)) {
      Some(left.indexWhere(_ == c) + 20)
    } else {
      None
    }
    k match{
      case Some(v) => QwertyLayout.getQwertyChar(v)
      case None => None
    }
  }
}
/** Defines the keyboard layout of DvorakLayout */
object DvorakLayout{
  val left = "12345\',.pyaoeui;qjkx"
  val right = "67890fgcrldhtnsbmwvz"
}
