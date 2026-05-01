// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class InteractiveEngineFixture extends munit.FunSuite {
  val engine = new Fixture[SQLiteInteractiveEngine] ("interactive engine"){
    var engine: SQLiteInteractiveEngine = null
    def apply() = engine
    override def beforeEach(context: BeforeEach): Unit = {
      val tcode_tbl_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/tcode_tbl.db"
      val mazegaki_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/mazegaki.db"
      val bushu_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/bushu.db"
      val jdbc_prefix = "jdbc:sqlite"

      Class.forName("org.sqlite.JDBC")

      engine = new SQLiteInteractiveEngine(jdbc_prefix, tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout
    }
    override def afterEach(context: AfterEach): Unit = {
      engine = null
    }
  }
  override def munitFixtures = List(engine)
  test("engine exists"){
    assert(engine() != null)
  }
}
