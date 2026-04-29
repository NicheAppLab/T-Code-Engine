package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import upickle.default._

trait MixedConverterDictionary{
  val dictionary: HashMap[String, Array[String]] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundMap = Map[String, Array[String]]()
      while(entry != null && foundMap.isEmpty){
        if (entry.getName == "mazegaki.msgpack"){
          foundMap = upickle.default.readBinary[Map[String, Array[String]]](zis)
        }
        if (foundMap.isEmpty) entry = zis.getNextEntry()
      }
      foundMap.to(HashMap)
    }
  }
}
