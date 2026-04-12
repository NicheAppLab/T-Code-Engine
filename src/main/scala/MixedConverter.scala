package io.github.nicheapplab.tcodeengine

trait MixedConverter{ this: MixedConverterDictionary =>
  def convert(str: String): Array[String] = {
    dictionary.get(str) match {
      case Some(array) => array
      case None => Array[String]()
    }
  }
  def convert(str: String, inflex: String): Array[String] = {
    convert(str++"—").map(_ ++ inflex)
  }
}
