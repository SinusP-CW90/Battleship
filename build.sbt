
name := "Battleship"
version := "0.5"

scalaVersion := "2.13.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-wordspec" % "3.2.2" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "com.google.inject.extensions" % "guice-assistedinject" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC2"

coverageExcludedPackages := "de\\.htwg\\.se\\.battleship\\.aview\\.gui;.*fileIOXmlImpl.*;.*Battleship"
