package io.github.nicheapplab.t_code_engine

import scala.io.Source
import scala.collection.immutable.HashMap
import scala.util.{Try, Using}


trait MixedConverter{ this: MixedConverterDictionary =>
  def convert(str: String): Array[String] = {
    dictionary.get(str) match {
      case Some(array) => array
      case None => Array[String]()
    }
  }
  def convert(str: String, inflex: String): Array[String] = {
    convert(str++"—").map(_ ++ inflex)
  }
}

trait MixedConverterDictionary{
  val dictionary = Using.resource(Source.fromResource("mazegaki.dic")){ source =>
    source.getLines().map { line =>
      line.split('/').map(_.trim).toList match {
        case hd::tl => (hd -> tl.toArray)
        case Nil => ("" -> Array[String]())
      }
    }.to(HashMap)
  }
}
