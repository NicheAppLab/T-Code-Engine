// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class InteractiveEngineTest extends InteractiveEngineFixture {

  test("記しゃ"){
    val ie = engine()
    "fjyijstt".foreach(ie.put(_))
    ie.inflexRight()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == (clue("記者")))
  }
  test("花だん"){
    val ie = engine()
    "fjd,hcnd".foreach(ie.put(_))
    ie.inflexRight()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("花壇"))
  }
  test("加える"){
    val ie = engine()
    "fjjtyslt;a".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("加える"))
  }
  test("相次ぐ"){
    val ie = engine()
    "fjjfpw.v.ddt".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("相次ぐ") )
  }
  test("相次ぐ火事により"){
    val ie = engine()
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
    val ie = engine()
    "jfjfibhtpd".foreach{ c=>
      ie.put(c)
    }
    val res = ie.commit()
    assert( clue(res) == clue("劇") )
  }
  test("Deleting"){
    val ie = engine()
    "jfjf".foreach{ c=>
      ie.put(c)
    }
    ie.backspace()
    ie.put('p')
    ie.put('w')
    ".v".foreach(ie.put(_))
    val res = ie.commit()
    assert( clue(res) == clue("相") )
  }
  test("丸"){
    val ie = engine()
    "jfjfjdnr".foreach{ c=>
      ie.put(c)
    }
    val res = ie.commit()
    assert( clue(res) == clue("丸") )
  }
  test("北陸"){
    val ie = engine()
    "fjpbjc" foreach (ie.put(_))
    assert( clue(ie.buffer.mkString) == clue("△北り") )
  }
}
