name := "elevator"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature")

connectInput in run   :=   true // Connects stdin to sbt during forked runs

outputStrategy        :=   Some(StdoutOutput) // Get rid of output prefix

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4.16",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)
