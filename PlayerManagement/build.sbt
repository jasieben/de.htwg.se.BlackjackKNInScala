ThisBuild / name          := "PlayerManagement"
ThisBuild / organization  := "de.htwg.se"
ThisBuild / version       := "0.0.1"
ThisBuild / scalaVersion  := "2.12.4"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "com.google.inject" % "guice" % "4.2.2"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.2"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.12"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.5"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.12"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0"