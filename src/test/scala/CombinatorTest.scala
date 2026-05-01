// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class CombinatorTest extends BatchEngineFixture {

  test("上"){
    val be = engine()
    val stroke = "ht"
    assertEquals(be.convert(stroke),"上")
  }
  test("七"){
    val be = engine()
    val stroke = "ib"
    assertEquals(be.convert(stroke), "七")
  }
  test("虍"){
    val be = engine()
    val left = be.combi.composite('上','七')
    assertEquals(left, Some('虍'))
  }
  test("虍 in composite"){
    val be = engine()
    val left = be.convert("jfibht")
    assertEquals(left, "虍")
  }
  test("劇"){
    val be = engine()
    val ans = be.combi.composite('虍', 'リ')
    assertEquals(ans, Some('劇'))
  }
  test("劇 in composite"){
    val be = engine()
    val ans = be.convert("jfjfibhtpd")
    assertEquals(ans, "劇")
  }
  test("劇丶 in composite"){
    val be = engine()
    val ans = be.convert("jfjfibhtpdjfjd")
    assertEquals(ans, "劇丶")
  }
  test("丶劇 in composite"){
    val be = engine()
    val ans = be.convert("jfjdjfjfibhtpd")
    assertEquals(ans, "丶劇")
  }
}
