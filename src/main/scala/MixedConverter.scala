package io.github.nicheapplab.t_codeengine

import scala.io.Source
import scala.collection.immutable.HashMap
import scala.util.{Try, Using}


object MixedConverter{
  private val dictionary = Using.resource(Source.fromResource("mazegaki.dic")){ source =>
    source.getLines().map { line =>
      line.split('/').map(_.strip()).toList match {
        case hd::tl => (hd -> tl.toArray)
        case Nil => ("" -> Array[String]())
      }
    }.to(HashMap)
  }

  def get(str: String): Array[String] = {
    dictionary.get(str) match {
      case Some(array) => array
      case None => Array[String]()
    }
  }
}
