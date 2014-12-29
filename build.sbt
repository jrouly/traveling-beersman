val spark = "org.apache.spark" %% "spark-core" % "1.2.0"
val graphx = "org.apache.spark" %% "spark-graphx" % "1.2.0"

lazy val commonSettings = Seq(
    organization := "net.rouly",
    version := "0.1.0",
    scalaVersion := "2.11.4"
  )

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "The Traveling Beersman",
    libraryDependencies += spark,
    libraryDependencies += graphx
  )
