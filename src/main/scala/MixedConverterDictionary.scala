package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.collection.mutable.ArrayBuffer
import scala.util.Using
import java.io.File
import java.util.zip.{ZipInputStream, ZipEntry}
import upickle.default._
import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.compiletime.uninitialized


abstract trait MixedConverterDictionary{
  def getCandidates(str: String): Array[String]
}

trait ArchivedMixedConverterDictionary extends MixedConverterDictionary {
  val dictionary = ArchivedMixedConverterDictionaryFactory.readDictionary()

  override def getCandidates(str: String): Array[String] = dictionary.get(str) match {
    case None => Array()
    case Some(array) => array
  }
}

object ArchivedMixedConverterDictionaryFactory{
  def readDictionary(): HashMap[String, Array[String]] = {
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

trait SQLiteMixedConverterDictionary (jdbc_prefix: String, mazegaki_path: String) extends MixedConverterDictionary {
  private var connection: java.sql.Connection = uninitialized
  private val dbfile = new File(mazegaki_path)
  if(!dbfile.exists()){
    println(s"dbfile doesn't exist at ${dbfile.getAbsolutePath}")
    extractResource("/tcode_dict.zip", dbfile)
  }

  connection = java.sql.DriverManager.getConnection(s"${jdbc_prefix}:${dbfile.getAbsolutePath}")

  private def extractResource(resourceName: String, destination: java.io.File): Unit = {

    val dictionary: HashMap[String, Array[String]] = ArchivedMixedConverterDictionaryFactory.readDictionary()

    val parent = dbfile.getParentFile()
    if (parent != null && !parent.exists()) {
      parent.mkdirs()
    }
    connection = java.sql.DriverManager.getConnection(s"jdbc:sqlite:${dbfile.getAbsolutePath}")
    val statement = connection.createStatement()
    statement.executeUpdate("CREATE TABLE IF NOT EXISTS mazegaki (key_str TEXT, value_str, TEXT, UNIQUE(key_str, value_str))")
    statement.executeUpdate("CREATE INDEX IF NOT EXISTS idx_key_str on mazegaki(key_str)")
    statement.close()

    val sql = "INSERT OR IGNORE INTO mazegaki (key_str, value_str) VALUES (?, ?)"

    connection.setAutoCommit(false)

    val pstmt: PreparedStatement = connection.prepareStatement(sql)

    try {
      for {
        (key, values) <- dictionary
        value <- values
      } {
        pstmt.setString(1, key)
        pstmt.setString(2, value)
        pstmt.addBatch()
      }
      pstmt.executeBatch()
      connection.commit()
    } catch {
      case e: Exception =>
        connection.rollback()
        throw e
    } finally {
      pstmt.close()
      connection.setAutoCommit(true)
    }
  }

  override def getCandidates(str: String) = {
    val sql = "SELECT value_str FROM mazegaki WHERE key_str = ?"
    val pstmt = connection.prepareStatement(sql)

    try{
      pstmt.setString(1, str)
      val rs = pstmt.executeQuery()

      val buffer = ArrayBuffer.empty[String]
      while(rs.next()){
        buffer += rs.getString("value_str")
      }
      rs.close()
      buffer.toArray
    } finally {
      pstmt.close()
    }
  }
}
