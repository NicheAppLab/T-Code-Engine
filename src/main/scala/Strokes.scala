package io.github.nicheapplab.tcodeengine

import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import upickle.default._

/** Provides the map of Qwerty two-keys-stroke to Japanese character */
object Strokes {
  private val dictionary: Array[Array[Char]] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundArray = Array[Array[Char]]()
      while(entry != null && foundArray.isEmpty){
        if (entry.getName == "tcode_tbl.msgpack"){
          foundArray = upickle.default.readBinary[Array[Array[Char]]](zis)
        }
        if (foundArray.isEmpty) entry = zis.getNextEntry()
      }
      foundArray
    }

  }

  def get(first: Int, second: Int):Char = dictionary(second)(first)
}

