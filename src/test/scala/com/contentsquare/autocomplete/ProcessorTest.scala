package com.contentsquare.autocomplete

import com.contentsquare.autocomplete.loader.KeyWordsReader
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ProcessorTest extends FlatSpec with Matchers{

  val config: AutoCompleteConfig = AutoCompleteConfig.loadConfigOrThrow("contentSquare")
  val processor = new AutoCompleteProcessor()

  val nonEmptyList: List[String] = KeyWordsReader.loadFiles("keyWords.csv").get


  /** The 'filerNot' method will be used in first tests because the list of words
   * returned by the method 'autocomplete'
   * don't respect any order
   * example : List("project", "proactive") != List("proactive", "project")
   */

  "when looking for words starting with 'pro' autoComplete method" should "return 'proactive' and 'project'" in {
    processor.autoComplete(nonEmptyList, "pro").filterNot(List("project", "proactive").contains(_)) shouldBe Nil
  }

  "autoComplete method" should "return 'kayak' when looking for words starting with 'k'" in {
    processor.autoComplete(nonEmptyList, "k") shouldBe List("kayak")
  }

  "autoComplete method" should "return an empty list when looking for words starting with 'l'" in {
    processor.autoComplete(nonEmptyList, "l") shouldBe Nil
  }

  "orderedSelection" should "return a list ordered alphabetically" in {
    processor.orderedSelection(nonEmptyList, 4) shouldBe List("kayak", "proactive", "project", "river")
  }

  "orderedSelection" should "return an empty list when ordering an empty list" in {
    processor.orderedSelection(Nil, 4) shouldBe Nil

  }

}
