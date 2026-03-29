// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.t_codeengine

class MixedConverterTest extends munit.FunSuite {

  // Dynamically generate MUnit tests for every lesson
  // Ignore tests where strokes haven't been implemented yet
  test("記しゃ"){
    assertEquals(MixedConverter.get("記しゃ").contains("記者"), true)
  }
  test("花だん"){
    assertEquals(MixedConverter.get("花だん").contains("花壇"), true)
  }
}
