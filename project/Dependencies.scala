import sbt._

object Dependencies {
  // Versions
  val playVersion = "2.5.8"
  val akkaVersion = "2.4.11"

  // Libraries
  val akkaStreams = "com.typesafe.akka" % "akka-stream_2.11" % akkaVersion

  val logback = "ch.qos.logback" %  "logback-classic" % "1.1.7"

  val macwireMacros = "com.softwaremill.macwire" %% "macros" % "2.2.4" % "provided"
  val macwireUtil = "com.softwaremill.macwire" %% "util" % "2.2.4"

  val play = "com.typesafe.play" % "play_2.11" % playVersion
  val playJson = "com.typesafe.play" % "play-json_2.11" % playVersion
  val playTest = "com.typesafe.play" % "play-test_2.11" % playVersion

  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
}
