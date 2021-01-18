package com.contentsquare.autocomplete

import com.contentsquare.autocomplete.loader.KeyWordsReader
import org.slf4j.{Logger, LoggerFactory}

import scala.annotation.tailrec
import scala.util._

class AutoCompleteProcessor {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  /** A word is simply a `String` */
  type Word = String

  /** A Selection is a list of words */
  type Selection = List[Word]

  def run(config: AutoCompleteConfig): Unit = {
    logger.info(s"Loading keyWords from: ${config.fileName}...")
    val keyWords: Try[List[String]] = KeyWordsReader.loadFiles(config.fileName)

    keyWords match {
      case Success(keyWords) => printAllResults(keyWords, config.selectedWords)
      case Failure(e) => logger.error(s"Couldn't load data from file :${config.fileName}, $e")
    }
  }

  /** Continue typing and get different suggestion
   * until type `quit` to exit the program
   */
  def printAllResults(keyWords: List[String], selectedWords: Int): Unit = {
    logger.info(s"Programme Started")

    while (keyWords.nonEmpty) {
      val typed: String = scala.io.StdIn.readLine("You'd like to look for:") // type a keyboard value
      if (typed == "quit") {
        logger.info(s"quit detected, program closing")
        sys.exit(0)
      } else {
        val allWords: Selection = autoComplete(keyWords, typed)
        val result = orderedSelection(allWords, selectedWords)

        println(result.mkString("\n")) //the output format
      }
    }
  }

  /** This method takes a list of words and a `typedValued`as parameter
   * and returns a selection of all words in the keywords file
   * starting with the typed value.
   * Note: the resulting selection is not sorted
   */
  def autoComplete(words: Selection, typedValue: Word): Selection = {
    /** this methode is called in tail recursive way to overcome
     * stackoverflow error once the Keywords file grow
     */
    @tailrec
    def autoCompleteAccum(wordsAccum: Selection, typedValueAccum: Word, Accum: Selection): Selection =
      wordsAccum match {
        case Nil => Accum
        case head :: tail =>
          if (head.slice(0, typedValueAccum.length).equalsIgnoreCase(typedValueAccum))
            autoCompleteAccum(tail, typedValueAccum, head :: Accum)
          else autoCompleteAccum(tail, typedValueAccum, Accum)
      }

    autoCompleteAccum(words, typedValue, List())
  }

  /** This Method returns a selected number of words ordered in an alphabetic way */
  def orderedSelection(words: Selection, selectedNumber: Int): Selection = {
    try {
      words
        .sortWith(_.compareTo(_) < 0)
        .take(selectedNumber)
    } catch {
      case error: Error =>
        logger.info(s"Failure, $error")
        throw error
    }
  }
}
