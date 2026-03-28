## T-Code Engine

T-Code engine provides T-Code IME core functions.

### Usage

Add dependency to your project.
```
libraryDependencies += "io.github.nicheapplab" %% "t-code-engine" % "0.2.0"
```
For maven or gradle, you might need to use `t-code-engine_3` instead, due to Scala3 package naming policy.


```
scala> import io.github.nicheapplab.t_codeengine._
scala> val engine = new Engine with QwertyLayout
scala> engine.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
val res0: String = "で、ので、では、を、のを、とを"
```

Alternatively, a predefined `DvorakLayout` can be specified.

For Kotlin, try this:

```
import io.github.nicheapplab.Engine as TCodeEngine
import io.github.nicheapplab.QwertyLayout

class QwertyEngine: TCodeEngine(), QwertyLayout
val engine = QwertyEngine()
engin.convert("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
```
