package com.contentsquare.autocomplete

object AutoCompleteApp extends App {

  val config: AutoCompleteConfig = AutoCompleteConfig.loadConfigOrThrow("contentSquare")

  new AutoCompleteProcessor().run(config)
}
