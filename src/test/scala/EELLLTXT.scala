// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.t_codeengine

class EELLLTXT extends munit.FunSuite {

  case class LessonData(name: String, expected: Array[String], strokes: Array[String])

  val lessons = List(
    LessonData(
      "1 (RL)",
      Array("の、が、のが"),
      Array("kdjd;sjdkd;s")
    ),
    LessonData(
      "2 (RL)",
      Array("て、ての、と、のと、とか"),
      Array("lajdlakdjdjajdkdjajdjaje")
    ),
    LessonData(
      "3 (RL)",
      Array(
        "に、のに、にて、は、のは、ては",
        "で、ので、では、を、のを、とを"
      ),
      Array(
        "kgjdkdkgjdkglajdjgjdkdjgjdlajg",
        "hgjdkdhgjdhgjgjd;gjdkd;gjdja;g"
      )
    ),
    LessonData(
      "4 (RL)",
      Array(
        "た。たのが、でた。な、なので、なにを",
        "なのに、はでなたなをたてにたてた。"
      ),
      Array(
        "kshfkskd;sjdhgkshflgjdlgkdhgjdlgkg;g",
        "lgkdkgjdjghglgkslg;gkslakgkslakshf"
      )
    ),
    LessonData(
      "5 (RL)",
      Array(
        "なにをしないでいたいの。ない。はい。いないが",
        "なし。しては、をした。なる。でる。いたがると",
        "はるになると、たのしい。しているのがいるし"
      ),
      Array(
        "lgkg;gjslghdhghdkshdkdhflghdhfjghdhfhdlghd;s", // Insert T-Code strokes for lesson 5
        "lgjshfjslajgjd;gjskshflg;ahfhg;ahfhdks;s;aja",
        "jg;akglg;ajajdkskdjshdhfjslahd;akd;shd;ajs"
      )
    ),
    LessonData(
      "6 (RL)",
      Array(
        "(て)(に)(を)(は)",
        "して(しないで)、",
        "この、もの、どの、おのおの、",
        "おる。こる。たどる。ともる。"
      ),
      Array(
        "kelaidkekgidke;gidkejgid", // Insert T-Code strokes for lesson 6
        "jslakejslghdhgidjd",
        "ufkdjdiakdjdkqkdjdoakdoakdjd",
        "oa;ahfuf;ahfkskq;ahfjaia;ahf"
      )
    ),
    LessonData(
      "7 (RL)",
      Array(
        "どこにもおもいものはない。",
        "とらない。とる。とれ。",
        "とられた、これらの、",
        "どれもしらないので、",
        "これをとられてはいられない。"
      ),
      Array(
        "kqufkgiaoaiahdiakdjglghdhf",
        "jaiglghdhfja;ahfja;ehf",
        "jaig;eksjduf;eigkdjd",
        "kq;eiajsiglghdkdhgjd",
        "uf;e;gjaig;elajghdig;elghdhf"
      )
    ),
    LessonData(
      "8 (RL)",
      Array(
        "ある。いる。うる。える。おる。",
        "あおい、もう、たえなる、",
        "あれ、あなた、あるいは、あてる。",
        "こうした、とうとう、どうにも、おこなう。",
        "あなたのいえは、(どうしても)あれをこえるものにはならない。"
      ),
      Array(
        "yf;ahfhd;ahfyd;ahflt;ahfoa;ahf",
        "yfoahdjdiaydjdksltlg;ajd",
        "yf;ejdyflgksjdyf;ahdjgjdyfla;ahf",
        "ufydjsksjdjaydjaydjdkqydkgiajdoauflgydhf",
        "yflgkskdhdltjgjdkekqydjslaiaidyf;e;guflt;aiakdkgjglgiglghdhf"
      )
    ),
    LessonData(
      "9 (RL)",
      Array(
        "される。もっと、さらに、であろう。さて、",
        "うしろ、さっと、いろいろ、したがって、",
        "あさっては、はれるであろう。",
        "うろうろしていて、おろされた。",
        "から、きた、くる、こい、かった。",
        "しかし、とき、なくては、できる。",
        "かっこでくくることができる。",
        "きっとはがきをくれる。"
      ),
      Array(
        "ug;e;ahfiajrjajdugigkgjdhgyfpsydhfuglajd",
        "ydjspsjdugjrjajdhdpshdpsjdjsks;sjrlajd",
        "yfugjrlajgjdjg;e;ahgyfpsydhf",
        "ydpsydpsjslahdlajdoapsug;ekshf",
        "jeigjdhrksjdjt;ajdufhdjdjejrkshf",
        "jsjejsjdjahrjdlgjtlajgjdhghr;ahf",
        "jejrufhgjtjt;aufja;shghr;ahf",
        "hrjrjajg;shr;gjt;e;ahf"
      )
    ),
    LessonData(
      "10 (RL)",
      Array(
        "いないわ。いや、いるよ。",
        "わるい、よい、こわい、やさしい。",
        "おはよう、っていうのはいやよ。",
        "(わが)や(わたしの)がよい。",
        "なくさせる。あせらせる。",
        "しない。しよう。した。しろ。せよ。",
        "さようなら、おせわになって、どうも。",
        "にせものではないかしら。"
      ),
      Array(
        "hdlghdyshfhdyejdhd;ajwhf",
        "ys;ahdjdjwhdjdufyshdjdyeugjshdhf",
        "oajgjwydjdjrlahdydkdjghdyejwhf",
        "keys;sidyekeysksjskdid;sjwhdhf",
        "lgjtuguw;ahfyfuwiguw;ahf",
        "jslghdhfjsjwydhfjskshfjspshfuwjwhf",
        "ugjwydlgigjdoauwyskglgjrlajdkqydiahf",
        "kguwiakdhgjglghdjejsighf"
      )
    ),
    LessonData(
      "11 (RL)",
      Array(
        "やがて、もっとも、とうとう、たとえ、もし、やたらに、",
        "あるいは、どうか、たえて、さらさら、せっかく、",
        "おもしろかろう。よろしかったら、くどくて、えらい。せわしいとき、",
        "ややこしかろう。こわかった。うれしくて、さもしい。やさしいとき、",
        "あのようなものにせっせとかかわっていないで、",
        "かれらにしたがってよいことをしなさい。"
      ),
      Array(
        "ye;slajdiajrjaiajdjaydjaydjdksjaltjdiajsjdyeksigkgjd",
        "yf;ahdjgjdkqydjejdksltlajdugigugigjduwjrjejtjd",
        "oaiajspsjepsydhfjwpsjsjejrksigjdjtkqjtlajdltighdhfuwysjshdjahrjd",
        "yeyeufjsjepsydhfufysjejrkshfyd;ejsjtlajdugiajshdhfyeugjshdjahrjd",
        "yfkdjwydlgiakdkguwjruwjajejeysjrlahdlghdhgjd",
        "je;eigkgjsks;sjrlajwhdufja;gjslgughdhf"
      )
    )
  )

  // Dynamically generate MUnit tests for every lesson
  lessons.foreach { lesson =>
    test(lesson.name) {
      // Ignore tests where strokes haven't been implemented yet
      assume(!lesson.strokes.contains("TODO"), s"T-Code strokes missing for ${lesson.name}")
      val engine = new Engine with QwertyLayout

      val result_array = lesson.strokes.map(str =>
        engine.convert(str)
      )
      val comp = result_array.zip(lesson.expected)
      comp.foreach((a,b) => assertEquals(a,b))
     }
  }
}
