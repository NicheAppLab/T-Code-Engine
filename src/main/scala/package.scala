package io.github.nicheapplab

/** Generates Japanese characters from key strokes defined by T-Code
  *
  * # Batch vs Interactive engine
  *
  * [[BatchEngine]] provides batched conversion
  * into Japanese. Prefix key strokes should be handled outside of the library.
  *
  * [[InteractiveEngine]], on the other hand, will handle prefix key strokes and
  * inflex specification internally. It is suitable for IMEs implementation.

  */
package object tcodeengine {}
