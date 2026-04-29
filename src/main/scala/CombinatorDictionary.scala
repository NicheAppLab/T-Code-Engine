package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import upickle.default._

trait CombinatorDictionary{
  val dictionary: HashMap[(Char, Char), Char] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundMap = Map[(Char, Char), Char]()
      while(entry != null && foundMap.isEmpty){
        if (entry.getName == "bushu.msgpack"){
          foundMap = upickle.default.readBinary[Map[(Char, Char), Char]](zis)
        }
        if (foundMap.isEmpty) entry = zis.getNextEntry()
      }
      foundMap.to(HashMap)
    }
  }

  def find(a: Char, b: Char):Option[Char] = {
    dictionary.get(a, b) orElse dictionary.get(b, a)
  }
}


