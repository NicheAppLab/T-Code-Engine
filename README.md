## T-Code Engine

T-Code engine provides T-Code IME core functions.

### Usage

Add dependency to your project.
```
libraryDependencies += "io.github.nicheapplab" %% "t-code-engine" % "0.6.3-SNAPSHOT"
```
For maven or gradle, you might need to use `t-code-engine_3` instead, due to Scala3 package naming policy.


```
import io.github.nicheapplab.tcodeengine._

// Specifies T-Code stroke table/mazegaki dictionary/kanji composition database files
// These database files will be created if doesn't exist

val tcode_tbl_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/tcode_tbl.db"
val mazegaki_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/mazegaki.db"
val bushu_path = System.getProperty("java.io.tempdir") ++ "/.t-code-engine/bushu.db"
val jdbc_prefix = "jdbc:sqlite"

val engine = new SQLiteBatchEngine(jdbc_prefix, tcode_tbl_path, mazegaki_path, bushu_path) with QwertyLayout

engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
// val res0: String = "で、ので、では、を、のを、とを"
```

Note: change `jdbc:sqlite` to `jdbc:sqldroid` for Android OS, to avoid `libsqlitejdbc.so is missing` exception on runtime.

Alternatively, a predefined `DvorakLayout` can be specified.

`InteractiveEngine` is used for user interactions, mainly used for IMEs implementation.
