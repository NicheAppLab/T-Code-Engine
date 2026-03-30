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
    ),
    LessonData(
      "12",
      Array(
        "「」「」「」「」「」",
        "「。」(「」、「、」、「。」)",
        "「あ」「か」「さ」「た」「な」",
        "「おはよう。」「さよなら。」",
        "そして、それは、その、ありそうな、",
        "おりおり、より、ありがとう。",
        "いそいそと、そこにいった。",
        "それよりかなりありそうな、",
        "きます。そうです。なにかする。",
        "まったく、まあ、そうすると、",
        "そのままかまわないで、",
        "すっかりすますまで、まってくれますか。"
      ),
      Array(
        ",gjv,gjv,gjv,gjv,gjv",
        ",ghfjvke,gjvjd,gjdjvjd,ghfjvid",
        ",gyfjv,gjejv,gugjv,gksjv,glgjv",
        ",goajgjwydhfjv,gugjwlgighfjv",
        ";bjslajd;b;ejgjd;bkdjdyfjc;bydlgjd",
        "oajcoajcjdjwjcjdyfjc;sjaydhf",
        "hd;bhd;bjajd;bufkghdjrkshf",
        ";b;ejwjcjelgjcyfjc;bydlgjd",
        "hrmd,fhf;bydhg,fhflgkgje,f;ahf",
        "mdjrksjtjdmdyfjd;byd,f;ajajd",
        ";bkdmdmdjemdyslghdhgjd",
        ",fjrjejc,fmd,fmdhgjdmdjrlajt;emd,fjehf"
      )
    ),
    LessonData(
      "13",
      Array(
        "もたない。もちます。もつ。もて。もとう。",
        "すなわち、つまり、ちっとも、ちりつつある。",
        "そのつどそちらにおうかがいいたします。",
        "ついでにわたしたちもおやつをもらいました。",
        "だ。です。ます。である。であります。",
        "だった。でした。ました。であった。でありました。",
        "「おれはそれだ」「わたしはこれです」",
        "「どちらさまがどれになさいますのですか」",
        "あちこちでこどもたちが、たいくつそうにしています。",
        "「いつ、もどりますか。」",
        "「そのうちもどるだろう。」"
     ),
      Array(
        "iakslghdhfia/amd,fhfia.dhfialahfiajaydhf",
        ",flgys/ajd.dmdjcjd/ajrjaiajd/ajc.d.dyf;ahf",
        ";bkd.dkq;b/aigkgoaydje;shdhdksjsmd,fhf",
        ".dhdhgkgysksjsks/aiaoaye.d;giaighdmdjskshf",
        "hchfhg,fhfmd,fhfhgyf;ahfhgyfjcmd,fhf",
        "hcjrkshfhgjskshfmdjskshfhgyfjrkshfhgyfjcmdjskshf",
        ",goa;ejg;b;ehcjv,gysksjsjguf;ehg,fjv",
        ",gkq/aigugmd;skq;ekglgughdmd,fkdhg,fjejv",
        "yf/auf/ahgufkqiaks/a;sjdkshdjt.d;bydkgjslahdmd,fhf",
        ",ghd.djdiakqjcmd,fjehfjv",
        ",g;bkdyd/aiakq;ahcpsydhfjv"
     ),
    ),
    LessonData(
      "14",
      Array(
        "めったに、みたいな、めそめそ、みちのく、",
        "ためしに、しめしめ、どろまみれ、",
        "こよみをめくる。ためるためのみに、",
        "きみみたいなめめしいやつとは、",
        "けっして、だんだん、そんな、だれ、だけを、",
        "けしからん。そうだけれども、けがしたんだ。",
        "やけにめんどうだけれど、",
        "ただただ、けしからんやつだ。",
        "みんなで「まとめだけではだめだ」といえますか。"
      ),
      Array(
        "lcjrkskgjd/gkshdlgjdlc;blc;bjd/g/akdjtjd",
        "kslcjskgjdjslcjslcjdkqpsmd/g;ejd",
        "ufjw/g;glcjt;ahfkslc;akslckd/gkgjd",
        "hr/g/gkshdlglclcjshdye.djajgjd",
        "nfjrjslajdhcndhcndjd;bndlgjdhc;ejdhcnf;gjd",
        "nfjsjeigndhf;bydhcnf;ekqiajdnf;sjsksndhchf",
        "yenfkglcndkqydhcnf;ekqjd",
        "kshckshcjdnfjsjeigndye.dhchf",
        "/gndlghg,gmdjalchcnfhgjghclchcjvjahdltmd,fjehf"
      )
    ),
    LessonData(
      "15",
      Array(
        "ばかり、ほとんど、じわじわ、ほほえみ、をば、",
        "ほおばる。じたばた、「ほら、しくじった。」",
        "ほどくのならば、まじめにほどいてほしい。",
        "じめじめすればするほど、じつにうっとうしい。",
        "まず、とげる。ずっと、もげる。",
        "うれしげに、よっつずつ、あげさげする。",
        "きずあとがずきずきとうずく。",
        "ずっとげらげらわらっていた。",
        "そればかりか、ずっとおよげずじまいだった。",
        "ほめられずくめ",
        "これにめげずに、しばしばいらしてください。"
      ),
      Array(
        ",wjejcjdpzjandkqjduxysuxysjdpzpzlt/gjd;g,wjd",
        "pzoa,w;ahfuxks,wksjd,gpzigjdjsjtuxjrkshfjv",
        "pzkqjtkdlgig,wjdmduxlckgpzkqhdlapzjshdhf",
        "uxlcuxlc,f;e,w,f;apzkqjdux.dkgydjrjaydjshdhf",
        "md,zjdja/z;ahf,zjrjajdia/z;ahf",
        "yd;ejs/zkgjdjwjr.d,z.djdyf/zug/z,f;ahf",
        "hr,zyfja;s,zhr,zhrjayd,zjthf",
        ",zjrja/zig/zigysigjrlahdkshf",
        ";b;e,wjejcjejd,zjrjaoajw/z,zuxmdhdhcjrkshf",
        "pzlcig;e,zjtlc",
        "uf;ekglc/z,zkgjdjs,wjs,whdigjslajthcughdhf"
      )
    ),
    LessonData(
      "15",
      Array(
        "012、210。",
        "10こ、2つ。",
        "2000人がたった1人になるまで",
        "10人から20人までにする。",
        "12345。5かける4は20。",
        "およそ、314くらいになる。",
        "はるは3月4月5月。15時は3時。"
      ),
      Array(
        "kf;d;fjd;f;dkfhf",
        ";dkfufjd;f.dhf",
        ";fkfkfkfma;sksjrks;dmakglg;amdhg",
        ";dkfmajeig;fkfmamdhgkg,f;ahf",
        ";d;fhaudldhfldjenf;audjg;fkfhf",
        "oajw;bjdha;dudjtighdkglg;ahf",
        "jg;ajghapfudpfldpfhf;dldmgjghamghf"
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
