// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

abstract class InteractiveEngineTestBase[T <: InteractiveEngine] extends munit.FunSuite {
  def engine: Fixture[T]
  override def munitFixtures = List(engine)

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
    "fjjfpw.v".foreach { c =>
      ie.put(c)
    }
    ie.convert()
    ".ddt".foreach(ie.put(_))
    ie.inflexLeft()
    ie.convert()
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("相次ぐ") )
  }
  test("相次ぐ火事により"){
    val ie = engine()
    "fjjfpw.v".foreach(ie.put(_))
    ie.convert()
    ".ddt".foreach(ie.put(_))
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
    "jfjfpdhtib".foreach{ c=>
      ie.put(c)
    }
    ie.convert()
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
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue("相") )
  }
  test("丸"){
    val ie = engine()
    "jfnrjfjd".foreach{ c=>
      ie.put(c)
    }
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue("丸") )
  }
  test("北陸"){
    val ie = engine()
    "fjpbjcjt" foreach (ie.put(_))
    println(s"input: ${ie.buffer.mkString}")
    ie.convert()
    println(s"candidates:${ie.candidates.mkString}")
    ie.selectCandidate(0)
    val res = ie.commit()
    assert( clue(res) == clue("北陸") )
  }
  test("頭-豆"){
    val ie = engine()
    "jfsc4,".foreach{ c =>
      ie.put(c)
    }
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue("頁"))
  }
  test("頭&題"){
    val ie = engine()
    assert( ie.combi.composite('頭', '題') == Some('頁'))
    "jfscjy".foreach{ c =>
      ie.put(c)
    }
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue("頁"))
  }
  test("項 from 工 + 頭&題"){
    val ie = engine()
    "jfmejfscjy".foreach(ie.put(_))
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue ("項"))
  }
  test("項 from 頭+工"){
    val ie = engine()
    "jfmesc".foreach(ie.put(_))
    ie.convert()
    val res = ie.commit()
    assert( clue(res) == clue ("項"))
  }
}
class SQLiteInteractiveEngineTest extends InteractiveEngineTestBase[SQLiteInteractiveEngine] with SQLiteInteractiveEngineFixture {
  override val engine = sqliteEngine
}
class ArchivedInteractiveEngineTest extends InteractiveEngineTestBase[ArchivedInteractiveEngine] with ArchivedInteractiveEngineFixture {
  override val engine = archivedEngine
}


