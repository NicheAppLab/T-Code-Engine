// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class InteractiveEngineTest extends munit.FunSuite {
  val tcode_tbl_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/tcode_tbl.db"
  val mazegaki_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/mazegaki.db"
  val bushu_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/bushu.db"

  val ie = new SQLiteInteractiveEngine(tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout

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
    }
    val res = ie.commit()
    assert( clue(res) == clue("劇") )
  }
  test("Deleting"){
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
    "jfjfjdnr".foreach{ c=>
      ie.put(c)
    }
    val res = ie.commit()
    assert( clue(res) == clue("丸") )
  }
  test("北陸"){
    "fjpbjc" foreach (ie.put(_))
    assert( clue(ie.buffer.mkString) == clue("△北り") )
  }
}
