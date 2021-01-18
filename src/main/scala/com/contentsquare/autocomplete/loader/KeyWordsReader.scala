package com.contentsquare.autocomplete.loader

import scala.io.Source
import scala.util._
import org.slf4j.{Logger, LoggerFactory}

import java.io.InputStream

object KeyWordsReader {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def loadFiles(fileName: String): Try[List[String]] = {
    val fileStream: InputStream = getClass.getResourceAsStream(s"/$fileName")
    Using(Source.fromInputStream(fileStream)) {_.getLines.toList.distinct}

  }
}

