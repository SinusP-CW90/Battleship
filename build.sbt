
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "Battleship",
    version := "1.0"
  )
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-wordspec" % "3.2.11" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.1.0"
libraryDependencies += "com.google.inject.extensions" % "guice-assistedinject" % "5.1.0"
//libraryDependencies += "com.google.inject" % "guice" % "4.2.3" cross CrossVersion.for3Use2_13
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.2" cross CrossVersion.for3Use2_13

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.2.1"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.13.2"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2" cross CrossVersion.for3Use2_13

//coverageExcludedPackages := "de\\.htwg\\.se\\.battleship\\.aview\\.gui;.*fileIOXmlImpl.*;.*Battleship"