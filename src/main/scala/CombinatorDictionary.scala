package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import java.io.{ObjectInputStream, InputStream}

trait CombinatorDictionary{
  val dictionary: HashMap[(Char, Char), Char] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundMap = HashMap[(Char, Char), Char]()
      while(entry != null && foundMap.isEmpty){
        if (entry.getName == "bushu.dat"){
          val ois = new ObjectInputStream(zis)
          foundMap = ois.readObject().asInstanceOf[HashMap[(Char, Char), Char]]
        }
        if (foundMap.isEmpty) entry = zis.getNextEntry()
      }
      foundMap
    }
  }

  def find(a: Char, b: Char):Option[Char] = {
    dictionary.get(a, b) orElse dictionary.get(b, a)
  }
}


