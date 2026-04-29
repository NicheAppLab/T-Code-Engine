// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
package io.github.nicheapplab.tcodeengine

class EELLLTXT100 extends munit.FunSuite {

  case class LessonData(name: String, expected: Array[String], strokes: Array[String])

  val lessons = List(
    LessonData(
      "101",
      Array(
        "ちかごろはひごとにみごとに",
        "なごんでごらん、さいごはごめん。",
        "ごてごて、ごみごみ、まごまご、すごすご",
        "このへんへは、へたなうたがたいへん",
        "へんてこにへこんだへなへなのへら",
        "すべてくらべてから、べつべつにのべるべきだ。",
        "のべつべったりせず、なるべくあべこべに",
        "べたべた、べらべら、すべすべ、つべこべ",
        "むしろ、うやむやですむ、むなしさ",
        "やむをえず、むだをむりやりおしこむ。",
        "むかむか、むずむず、むっくり"
      ),
      Array(
        "/aje/kpsjgm./kjakg/g/kjakg",
        "lg/kndhg/kigndjdughd/kjg/klcndhf",
        "/kla/klajd/k/g/k/gjdmd/kmd/kjd,f/k,f/k",
        "ufkd;;nd;;jgjd;;kslgydks;skshd;;nd",
        ";;ndlaufkg;;ufndhc;;lg;;lgkd;;ig",
        ",fyllajtigyllajeigjdyl.dyl.dkgkdyl;aylhrhchf",
        "kdyl.dyljrksjcuw,zjdlg;ayljtyfylufylkg",
        "ylksylksjdyligyligjd,fyl,fyljd.dylufyl",
        "yhjspsjdydyeyhyehg,fyhjdyhlgjsug",
        "yeyh;glt,zjdyhhc;gyhjcyejcoajsufyhhf",
        "yhjeyhjejdyh,zyh,zjdyhjrjtjc"
      )
    ),
    LessonData(
      "102",
      Array(
        "ぞっとするほどおぞましいぞ。",
        "どうぞ、ぞろりそろったなぞに、それぞれこぞって",
        "ぞくぞく、ぞろぞろ、もぞもぞ",
        "のんびりするたびに、びしびしあびる。",
        "および、びっしりかびがはびこったら、",
        "びりびり、びくびく、ちびちび、きびきび",
        "ひとまず、ひとつひとつひずませる。",
        "ひりひりして、ひじのまひがひどくなった。",
        "ひらひら、ひそひそ、ひんやり、ひっそり",
        "ちょうどいっしょにちょうだいしましょう。",
        "ちょっときょろきょろ、きょうはしょうがない。",
        "ひょろひょろ、びしょびしょ、"
      ),
      Array(
        ".ujrja,f;apzkqoa.umdjshd.uhf",
        "kqyd.ujd.upsjc;bpsjrkslg.ukgjd;b;e.u;euf.ujrla",
        ".ujt.ujtjd.ups.upsjdia.uia.u",
        "kdndjojc,f;aksjokgjdjojsjojsyfjo;ahf",
        "oajwjojdjojrjsjcjejo;sjgjoufjrksigjd",
        "jojcjojcjdjojtjojtjd/ajo/ajojdhrjohrjo",
        "m.jamd,zjdm.ja.dm.ja.dm.,zmduw;ahf",
        "m.jcm.jcjslajdm.uxkdmdm.;sm.kqjtlgjrkshf",
        "m.igm.igjdm.;bm.;bjdm.ndyejcjdm.jr;bjc",
        "/a,.ydkqhdjrjs,.kg/a,.ydhchdjsmdjs,.ydhf",
        "/a,.jrjahr,.pshr,.psjdhr,.ydjgjs,.yd;slghdhf",
        "m.,.psm.,.psjdjojs,.jojs,.jd"
      )
    ),
    LessonData(
      "103",
      Array(
        "ぐっとたじろぐ、ものぐさもぐら。まぐれでほぐれる。",
        "ぐずぐずしない、くれぐれもはぐれずに。",
        "ぐいぐい、ぐるぐる、ちぐはぐ、もぐもぐ",
        "ぶきっちょなずぶのしろうとがかぶれたやぶさめ",
        "しぶとくぶつかっても、ぶよぶよのこぶがだぶつくだけ。",
        "ぶらぶら、ぶかぶか、だぶだぶ、がぶがぶ",
        "かねがねうわさの、ひねくれためがね",
        "ねえさんにこまねずみのまねをねだった。",
        "ねばねば、ねちねち、うねうね、くねくね",
        "ぎこちなくちぎる、",
        "ぎりぎりでとぎれるせせらぎのおと、",
        "ぎらぎら、ぎょろぎょろ、どぎまぎ、とぎれとぎれ",
        "ふとふりかえると、ふしぎにふさわしい。",
        "ふらふら、ふんふん、ふうふう、ふさふさ"
      ),
      Array(
        "dtjrjaksuxpsdtjdiakddtugiadtighfmddt;ehgpzdt;e;ahf",
        "dt,zdt,zjslghdjdjt;edt;eiajgdt;e,zkghf",
        "dthddthdjddt;adt;ajd/adtjgdtjdiadtiadt",
        "sbhrjr/a,.lg,zsbkdjspsydja;sjesb;eksyesbuglc",
        "jssbjajtsb.djejrlaiajdsbjwsbjwkdufsb;shcsb.djthcnfhf",
        "sbigsbigjdsbjesbjejdhcsbhcsbjd;ssb;ssb",
        "jefd;sfdydysugkdjdm.fdjt;ekslc;sfd",
        "fdltugndkgufmdfd,z/gkdmdfd;gfdhcjrkshf",
        "fd,wfd,wjdfd/afd/ajdydfdydfdjdjtfdjtfd",
        "qfuf/algjt/aqf;ajd",
        "qfjcqfjchgjaqf;e;auwuwigqfkdoajajd",
        "qfigqfigjdqf,.psqf,.psjdkqqfmdqfjdjaqf;ejaqf;e",
        "rsjarsjcjelt;ajajdrsjsqfkgrsugysjshdhf",
        "rsigrsigjdrsndrsndjdrsydrsydjdrsugrsug"
      )
    ),
    LessonData(
      "104",
      Array(
        "わざといざこざを、なおさりにせざるを",
        "あざといおふざけにはうんざり。",
        "ざらざら、ざわざわ、ぎざぎざ、むざむざ",
        "ぬかるみならぬ、ぬけぬけとかけぬける。",
        "ぬくぬく、ぬるぬる、びしょぬれ、きぬぎぬ",
        "つれづれなるまま、つくづくつづりつづける。",
        "くんづほぐれつ、まつばづえ、こづかい、てづかみ、",
        "みかづき、おじけづく、てづくり、ひづけ、たちづめ",
        "やぼなぼやきには、ぼくはほぼ、とぼけられる。",
        "ぼんやりぼやけた、みすぼらしいつぼみ",
        "ぼやぼや、ぼろぼろ、とぼとぼ、しょぼしょぼ",
        "きゅうきゅう、びゅうびゅう、しゅんと、にゅっと",
        "しょっちゅう、じゅうたん、おおきゅう、うれしゅうございます。",
        "きゅん、ぎゅう、しゅつ、じゅつ、にゅう、りゅう"
      ),
      Array(
        "ysfojahdfouffo;gjdlgoaugjckguwfo;a;g",
        "yffojahdoarsfonfkgjgydndfojchf",
        "foigfoigjdfoysfoysjdqffoqffojdyhfoyhfo",
        "a.je;a/glgiga.jda.nfa.nfjajenfa.nf;ahf",
        "a.jta.jtjda.;aa.;ajdjojs,.a.;ejdhra.qfa.",
        ".d;eek;elg;amdmdjd.djtekjt.dekjc.deknf;ahf",
        "jtndekpzdt;e.djdmd.d,wekltjdufekjehdjdlaekje/gjd",
        "/gjeekhrjdoauxnfekjtjdlaekjtjcjdm.eknfjdks/aeklc",
        "yecmlgcmyehrkgjgjdcmjtjgpzcmjdjacmnfig;e;ahf",
        "cmndyejccmyenfksjd/g,fcmigjshd.dcm/g",
        "cmyecmyejdcmpscmpsjdjacmjacmjdjs,.cmjs,.cm",
        "hrq.ydhrq.ydjdjoq.ydjoq.ydjdjsq.ndjajdkgq.jrja",
        "js,.jr/aq.ydjduxq.ydksndjdoaoahrq.ydjdyd;ejsq.yd/kfohdmd,fhf",
        "hrq.ndjdqfq.ydjdjsq..djduxq..djdkgq.ydjdjcq.yd"
      )
    ),
    LessonData(
      "105",
      Array(
        "かぜ、ぜいぜい。ぜったいぜいたくしないぜ。",
        "ぜひ、まぜずに、おぜんのぜんざいを",
        "ぜんぜん、ぜんまい、なぜ、ふぜい、あぜ",
        "ちぢに、ちぢむ。はなぢ、あとぢえ、みぢかな、ばかぢから",
        "もらいぢち、おだわらぢょうちん、ひぢりめん、こぢんまり"
      ),
      Array(
        "je;3jd;3hd;3hdhf;3jrkshd;3hdksjtjslghd;3hf",
        ";3m.jdmd;3,zkgjdoa;3ndkd;3ndfohd;g",
        ";3nd;3ndjd;3ndmdhdjdlg;3jdrs;3hdjdyf;3",
        "/a8vkgjd/a8vyhhfjglg8vjdyfja8vltjd/g8vjelgjd,wje8vjeig",
        "iaighd8v/ajdoahcysig8v,.yd/andjdm.8vjclcndjduf8vndmdjc"
      )
    ),
    LessonData(
      "106",
      Array(
        "やっぱりおおざっぱでも、てきぱき、ぱっとひっぱろう。",
        "ぱらぱら、ぱちぱち、すぱすぱ、ぱぱっと",
        "ちょっぴりぴろぴり、ぴったりぴちぴち。",
        "ぴかぴか、ぴちゃぴちゃ、ぴょこぴょこ",
        "たっぷりちんぷんかんぷん、でっぷりぷくぷく",
        "ぷかぷか、ぷりんぷりん、たぷたぷ、あっぷあっぷ",
        "ぺしゃんこでぺらぺらのはんぺんをぺろり。",
        "ぺたぺた、ぺこぺこ、のっぺり、すっぺり",
        "ちっぽけなのっぽのたんぽぽ、よっぽどぽんこつっぽい",
        "ぽたぽた、ぽろぽろ、すぽすぽ、がっぽり"
      ),
      Array(
        "yejr1ajcoaoafojr1ahgiajdlahr1ahrjd1ajrjam.jr1apsydhf",
        "1aig1aigjd1a/a1a/ajd,f1a,f1ajd1a1ajrja",
        "/a,.jr1sjc1sps1sjcjd1sjrksjc1s/a1s/ahf",
        "1sje1sjejd1s/att1s/attjd1s,.uf1s,.uf",
        "ksjr1djc/and1dndjend1dndjdhgjr1djc1djt1djt",
        "1dje1djejd1djcnd1djcndjdks1dks1djdyfjr1dyfjr1d",
        "1fjsttndufhg1fig1figkdjgnd1fnd;g1fpsjchf",
        "1fks1fksjd1fuf1fufjdkdjr1fjcjd,fjr1fjc",
        "/ajr1gnflgkdjr1gkdksnd1g1gjdjwjr1gkq1gnduf.djr1ghd",
        "1gks1gksjd1gps1gpsjd,f1g,f1gjd;sjr1gjc"
      )
    )
  )

  // Dynamically generate MUnit tests for every lesson
  lessons.foreach { lesson =>
    test(lesson.name) {
      // Ignore tests where strokes haven't been implemented yet
      assume(!lesson.strokes.contains("TODO"), s"T-Code strokes missing for ${lesson.name}")

      val tcode_tbl_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/tcode_tbl.db"
      val mazegaki_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/mazegaki.db"
      val bushu_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/bushu.db"

      val engine = new SQLiteBatchEngine(tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout

      val result_array = lesson.strokes.map(str =>
        engine.convert(str)
      )
      val comp = result_array.zip(lesson.expected)
      comp.foreach((a,b) => assertEquals(a,b))
     }
  }
}
