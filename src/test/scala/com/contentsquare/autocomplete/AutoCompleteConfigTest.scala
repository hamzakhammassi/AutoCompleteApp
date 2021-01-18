package com.contentsquare.autocomplete

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AutoCompleteConfigTest extends AnyFunSuite with Matchers {

  test("Conf should be loaded without throwing exceptions") {
    val conf: AutoCompleteConfig = AutoCompleteConfig.loadConfigOrThrow("contentSquare")

    noException should be thrownBy conf
  }
}
