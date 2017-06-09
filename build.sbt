name := """mastermind"""
organization := "pl.zalas"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "commons-io" % "commons-io" % "2.4"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test
libraryDependencies += "info.cukes" % "cucumber-scala_2.11" % "1.2.4" % Test
libraryDependencies += "info.cukes" % "cucumber-junit" % "1.2.4" % Test
libraryDependencies += "junit" % "junit" % "4.12" % Test
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "pl.zalas.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "pl.zalas.binders._"

coverageMinimum := 97
coverageFailOnMinimum := true
coverageHighlighting := true
coverageExcludedPackages := "<empty>;Reverse.*;router\\.*"
