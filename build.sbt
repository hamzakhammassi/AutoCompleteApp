name := "Autocomplete"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies += "com.typesafe" % "config" % "1.3.3"
libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.14.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % Test
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)


