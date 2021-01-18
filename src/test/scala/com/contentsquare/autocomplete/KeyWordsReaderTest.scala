package com.contentsquare.autocomplete

import com.contentsquare.autocomplete.loader.KeyWordsReader
import org.scalatest.matchers.should.Matchers
import org.scalatest.funsuite.AnyFunSuite

class KeyWordsReaderTest extends AnyFunSuite with Matchers {

  val result: List[String] = KeyWordsReader.loadFiles("keyWords.csv").get

  test("keyWordLoader should correctly load data from keywords file") {
    result should contain theSameElementsAs List("project", "river", "kayak", "proactive")
  }

  test("keyWordLoader should load non duplicated set of data from keywords file") {
    result.size shouldBe 4
  }
}
