package io.github.nicheapplab.tcodeengine

import scala.collection.immutable.HashMap
import scala.util.Using
import java.util.zip.{ZipInputStream, ZipEntry}
import java.io.File
import upickle.default._
import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.compiletime.uninitialized


abstract trait CombinatorDictionary{
  def find(a: Char, b: Char): Option[Char]
}

trait ArchivedCombinatorDictionary extends CombinatorDictionary {
  val dictionary: HashMap[(Char, Char), Char] = ArchivedCombinatorDictionaryFactory.readDictionary()

  override def find(a: Char, b: Char): Option[Char] = {
    dictionary.get(a, b) orElse dictionary.get(b, a)
  }
}

object ArchivedCombinatorDictionaryFactory extends ArchivedCombinatorDictionary {
  def readDictionary(): HashMap[(Char, Char), Char] = {
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
  override def find(a: Char, b: Char): Option[Char] = {
    dictionary.get(a, b) orElse dictionary.get(b, a)
  }
}

trait SQLiteCombinatorDictionary(jdbc_prefix: String, bushu_path: String) extends CombinatorDictionary{

  private var connection: java.sql.Connection = uninitialized
  private val dbfile = new File(bushu_path)
  if(!dbfile.exists()){
    extractResource("/tcode_dict.zip", dbfile)
  }

  Class.forName("org.sqlite.JDBC")
  connection = java.sql.DriverManager.getConnection(s"${jdbc_prefix}:${dbfile.getAbsolutePath}")

  private def extractResource(resourceName: String, destination: java.io.File): Unit = {

    val dictionary: HashMap[(Char, Char), Char] = ArchivedCombinatorDictionaryFactory.readDictionary()

    val parent = dbfile.getParentFile()
    if (parent != null && !parent.exists()) {
      parent.mkdirs()
    }
    Class.forName("org.sqlite.JDBC")
    connection = java.sql.DriverManager.getConnection(s"${jdbc_prefix}:${dbfile.getAbsolutePath}")
    val statement = connection.createStatement()
    statement.executeUpdate("CREATE TABLE IF NOT EXISTS bushu (char1 TEXT, char2 TEXT, result TEXT, PRIMARY KEY (char1, char2))")
    statement.close()

    val sql = "INSERT OR IGNORE INTO bushu (char1, char2, result) VALUES (?, ?, ?)"
    connection.setAutoCommit(false)

    val pstmt: PreparedStatement = connection.prepareStatement(sql)

    try {
      dictionary.foreach { case ( (c1, c2)-> res) =>
        pstmt.setString(1, c1.toString)
        pstmt.setString(2, c2.toString)
        pstmt.setString(3, res.toString)
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

  override def find(a: Char, b: Char): Option[Char] = {
    val sql = "SELECT result FROM bushu WHERE (char1 = ? AND char2 = ?) LIMIT 1"
    val pstmt = connection.prepareStatement(sql)

    def query(c1: Char, c2: Char): Option[Char] = {
      pstmt.setString(1, c1.toString)
      pstmt.setString(2, c2.toString)
      val rs: ResultSet = pstmt.executeQuery()
      val res = if (rs.next()) Some(rs.getString("result").charAt(0)) else None
      rs.close()
      res
    }

    try {
      // Implement: dictionary.get(a, b) orElse dictionary.get(b, a)
      query(a, b).orElse(query(b, a))
    } finally {
      pstmt.close()
    }
  }
}
