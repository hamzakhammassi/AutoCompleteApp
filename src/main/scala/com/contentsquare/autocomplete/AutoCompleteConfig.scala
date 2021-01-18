package com.contentsquare.autocomplete

import com.typesafe.config.{Config, ConfigFactory}
import pureconfig._
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

case class AutoCompleteConfig(fileName: String, selectedWords: Int)

object AutoCompleteConfig {
  implicit def hint[T]: ProductHint[T] = ProductHint[T](ConfigFieldMapping(identity))

  def loadConfigOrThrow(baseConf: String, config: Config = ConfigFactory.load()): AutoCompleteConfig =
    ConfigSource.fromConfig(config.getConfig(baseConf)).loadOrThrow[AutoCompleteConfig]
}
