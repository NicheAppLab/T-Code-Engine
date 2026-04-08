// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class MixedConverterTest extends munit.FunSuite {

  val mixed = new MixedConverter with MixedConverterDictionary

  test("記しゃ"){
    assertEquals(mixed.convert("記しゃ").contains("記者"), true)
  }
  test("花だん"){
    assertEquals(mixed.convert("花だん").contains("花壇"), true)
  }
  test("加える"){
    val candidates = mixed.convert("くわえ","る")
    assertEquals(candidates.toList, List("加える"))
  }
}
