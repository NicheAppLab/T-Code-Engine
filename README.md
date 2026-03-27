## T-Code Engine

T-Code engine provides T-Code IME core functions.

### Usage

```
scala> import com.nicheapplab.t_codeengine._
scala> val engine = new Engine with QwertyLayout
scala> engine.convertQwerty("hgjdkdhgjdhgjgjd;gjdkd;gjdja;g")
val res0: String = "で、ので、では、を、のを、とを"
```

Alternatively, a predefined `DvorakLayout` can be specified.
