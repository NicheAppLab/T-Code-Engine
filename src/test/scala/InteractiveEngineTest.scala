// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class InteractiveEngineTest extends munit.FunSuite {

  val ie = new InteractiveEngine with QwertyLayout

  test("記しゃ"){
    "fjyijstt".foreach(ie.put(_))
    ie.inflexRight()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == (clue("記者")))
  }
  test("花だん"){
    "fjd,hcnd".foreach(ie.put(_))
    ie.inflexRight()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("花壇"))
  }
  test("加える"){
    "fjjtyslt;a".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("加える"))
  }
  test("相次ぐ"){
    "fjjfpw.v.ddt".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("相次ぐ") )
  }
  test("相次ぐ火事により"){
    "fjjfpw.v.ddt".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    "fjjeux".foreach(ie.put(_))
    ie.inflexRight()
    ie.convert()
    ie.selectCandidate(3)
    "kgjwjc".foreach(ie.put(_))
    val res = ie.commit()
    assert( clue(res) == clue("相次ぐ火事により") )
  }
  test("劇"){
    "jfjfibhtpd".foreach{ c=>
      ie.put(c)
      println(s"Out: ${ie.outputBuffer}, Buff: ${ie.buffer}, Last: ${ie.lastChar}")
    }
    val res = ie.commit()
    assert( clue(res) == clue("劇") )
  }
  test("Deleting"){
    "jfjf".foreach{ c=>
      ie.put(c)
    }
    ie.backspace()
    "pw.v".foreach(ie.put(_))
    assert( clue(ie.outputBuffer.mkString) == clue("相"))
    assert( clue(ie.buffer).size == 0 )
    val res = ie.commit()
    assert( clue(res) == clue("相") )
  }
}
