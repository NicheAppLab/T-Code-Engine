// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class CombinatorTest extends munit.FunSuite {

  val engine = new BatchEngine with QwertyLayout

  // Dynamically generate MUnit tests for every lesson
  // Ignore tests where strokes haven't been implemented yet
  test("上"){
    val stroke = "ht"
    assertEquals(engine.convert(stroke),"上")
  }
  test("七"){
    val stroke = "ib"
    assertEquals(engine.convert(stroke), "七")
  }
  test("虍"){
    val left = engine.combi.composite('上','七')
    assertEquals(left, Some('虍'))
  }
  test("虍 in composite"){
    val left = engine.convert("jfibht")
    assertEquals(left, "虍")
  }


  test("劇"){
    val ans = engine.combi.composite('虍', 'リ')
    assertEquals(ans, Some('劇'))
  }
  test("劇 in composite"){
    val ans = engine.convert("jfjfibhtpd")
    assertEquals(ans, "劇")
  }
  test("劇丶 in composite"){
    val ans = engine.convert("jfjfibhtpdjfjd")
    assertEquals(ans, "劇丶")
  }
  test("丶劇 in composite"){
    val ans = engine.convert("jfjdjfjfibhtpd")
    assertEquals(ans, "丶劇")
  }



}
