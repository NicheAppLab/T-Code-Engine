package io.github.nicheapplab.tcodeengine

import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import java.io.{ObjectInputStream, InputStream}
/** Provides the map of Qwerty two-keys-stroke to Japanese character */
object Strokes {
  private val dictionary: Array[Array[Char]] = {
    val inputStream = getClass.getResourceAsStream("/tcode_dict.zip")
    Using.resource(new ZipInputStream(inputStream)){ zis =>
      var entry: ZipEntry = zis.getNextEntry
      var foundArray = Array[Array[Char]]()
      while(entry != null && foundArray.isEmpty){
        if (entry.getName == "tcode_tbl.dat"){
          val ois = new ObjectInputStream(zis)
          foundArray = ois.readObject().asInstanceOf[Array[Array[Char]]]
        }
        if (foundArray.isEmpty) entry = zis.getNextEntry()
      }
      foundArray
    }

  }

  def get(first: Int, second: Int):Char = dictionary(second)(first)
}

