package io.github.nicheapplab.tcodeengine

import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import java.io.File
import upickle.default._
import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.compiletime.uninitialized

/** Provides the map of Qwerty two-keys-stroke to Japanese character */

abstract trait Strokes {
  def getChar(first: Int, second: Int): Char
}
trait ArchivedStrokes extends Strokes{
  private val dictionary = ArchivedStrokesFactory.readDictionary()
  override def getChar(first: Int, second: Int): Char = dictionary(second)(first)
}
object ArchivedStrokesFactory extends ArchivedStrokes {
  def readDictionary(): Array[Array[Char]] = {
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
}

trait SQLiteStrokes (dbPath: String) extends Strokes{
  
  private var connection: java.sql.Connection = uninitialized
  private val dbfile = new File(dbPath)
  if(!dbfile.exists()){
    println(s"dbfile doesn't exist at ${dbfile.getAbsolutePath}")
    extractResource("/tcode_dict.zip", dbfile)
  }

  Class.forName("org.sqlite.JDBC")
  connection = java.sql.DriverManager.getConnection(s"jdbc:sqlite:${dbfile.getAbsolutePath}")

  private def extractResource(resourceName: String, destination: java.io.File): Unit = {

    val dictionary: Array[Array[Char]] = ArchivedStrokesFactory.readDictionary()

    val parent = dbfile.getParentFile()
    if (parent != null && !parent.exists()) {
      parent.mkdirs()
    }
    Class.forName("org.sqlite.JDBC")
    connection = java.sql.DriverManager.getConnection(s"jdbc:sqlite:${dbfile.getAbsolutePath}")
    val statement = connection.createStatement()
    statement.executeUpdate("CREATE TABLE IF NOT EXISTS tcode_tbl (idx1 INTEGER, idx2 INTEGER, character TEXT, PRIMARY KEY (idx1, idx2))")
    statement.close()

    val sql = "INSERT OR IGNORE INTO tcode_tbl (idx1, idx2, character) VALUES (?, ?, ?)"
    connection.setAutoCommit(false)

    val pstmt: PreparedStatement = connection.prepareStatement(sql)

    try {
      for {
        i <- dictionary.indices
        j <- dictionary(i).indices
      } {
        pstmt.setInt(1, i)
        pstmt.setInt(2, j)
        pstmt.setString(3, dictionary(i)(j).toString)
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
  def getChar(first: Int, second: Int): Char = {
    val sql = "SELECT character FROM tcode_tbl WHERE idx1 = ? AND idx2 = ?"
    val pstmt: PreparedStatement = connection.prepareStatement(sql)

    try{
      pstmt.setInt(1, second)
      pstmt.setInt(2, first)

      val rs: ResultSet = pstmt.executeQuery()
      if (rs.next()) {
        rs.getString("character").charAt(0)
      } else {
        ' '
      }
    } finally {
      pstmt.close()
    }
  }
}
