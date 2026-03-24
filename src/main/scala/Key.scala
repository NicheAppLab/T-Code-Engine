package com.nicheapplab.t_codeengine
sealed abstract class Key {
  def getQwerty: Option[Char]
}
object Key {
  def fromQwerty(c: Char): Option[Key] = {
    (LKey.fromQwerty(c), RKey.fromQwerty(c)) match {
      case (Some(k), None) => Some(k)
      case (None, Some(k)) => Some(k)
      case _               => None
    }
  }
  def fromDvorak(c: Char): Option[Key] = {
    (LKey.fromDvorak(c), RKey.fromDvorak(c)) match {
      case (Some(k), None) => Some(k)
      case (None, Some(k)) => Some(k)
      case _               => None
    }
  }
}
case class LKey(v: Int) extends Key {
  override def getQwerty: Option[Char] = {
    try {
      Some(LKey.q_array.charAt(v))
    } catch {
      case e => {
        println(e)
        None
      }
    }
  }
}
object LKey {
  val q_array = "12345qwertasdfgzxcvb"
  val d_array = "12345\',.pyaoeui;qjkx"
  def fromQwerty(c: Char): Option[Key] = {
    if (q_array.contains(c)) {
      Some(LKey(q_array.indexWhere(_ == c)))
    } else {
      None
    }
  }
  def fromDvorak(c: Char): Option[Key] = {
    if (d_array.contains(c)) {
      Some(LKey(q_array.indexWhere(_ == c)))
    } else {
      None
    }
  }
}
case class RKey(v: Int) extends Key {
  override def getQwerty: Option[Char] = {
    try {
      Some(RKey.q_array.charAt(v))
    } catch {
      case e => {
        println(e)
        None
      }
    }
  }
}
object RKey {
  val q_array = "67890yuiophjkl;nm,./"
  val d_array = "67890fgcrldhtnsbmwvz"
  def fromQwerty(c: Char): Option[Key] = {
    if (q_array.contains(c)) {
      Some(RKey(q_array.indexWhere(_ == c)))
    } else {
      None
    }
  }
  def fromDvorak(c: Char): Option[Key] = {
    if (d_array.contains(c)) {
      Some(RKey(q_array.indexWhere(_ == c)))
    } else {
      None
    }
  }

}


