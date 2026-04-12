package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import java.io.{ObjectInputStream, InputStream}

trait MixedConverterDictionary{
  val dictionary: HashMap[String, Array[String]] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundMap = HashMap[String, Array[String]]()
      while(entry != null && foundMap.isEmpty){
        if (entry.getName == "mazegaki.dat"){
          val ois = new ObjectInputStream(zis)
          foundMap = ois.readObject().asInstanceOf[HashMap[String, Array[String]]]
        }
        if (foundMap.isEmpty) entry = zis.getNextEntry()
      }
      foundMap
    }
  }
}
