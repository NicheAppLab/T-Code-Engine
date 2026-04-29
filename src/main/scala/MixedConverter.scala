package io.github.nicheapplab.tcodeengine

trait MixedConverter{ this: MixedConverterDictionary =>
  def convert(str: String): Array[String] = getCandidates(str)

  def convert(str: String, inflex: String): Array[String] = {
    convert(str++"—").map(_ ++ inflex)
  }
}
