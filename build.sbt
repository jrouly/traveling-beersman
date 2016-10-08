import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.travelingbeersman",
  version := "0.0.0",
  scalaVersion := "2.11.8"
)

lazy val `traveling-beersman` = project.in(file("."))
  .settings(commonSettings: _*)
  .aggregate(common, web, api, map)

lazy val common = project.in(file("common"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      macwireMacros,
      macwireUtil,
      play,
      scalaLogging
    )
  )

lazy val web = project.in(file("web"))
  .dependsOn(common)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      logback,
      macwireMacros,
      macwireUtil,
      play,
      playTest,
      playJson,
      scalaLogging
    )
  )

lazy val api = project.in(file("api"))
  .settings(commonSettings: _*)

lazy val map = project.in(file("map"))
  .settings(commonSettings: _*)
