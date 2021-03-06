/******************** Common project settings **********************/

name := """orome"""
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"
scalacOptions ++= Seq(
  "-unchecked",             // Enable additional warnings where generated code depends on assumptions
  "-deprecation",           // Emit warning and location for usages of deprecated APIs
  "-explaintypes",          // Explain type errors in more detail
  "-feature",               // Emit warning and location for usages of features that should be imported explicitly
  "-language:_",            // Enable or disable language features: '_' for all
  "-target:jvm-1.8",        // Specify which backend to use
  "-encoding", "UTF-8",     // Specify character encoding used by source files
//  "-optimise,"               // Generates faster bytecode by applying optimisations to the program
  // ******* Advanced options *******
  "-Xcheckinit",            // Wrap field accessors to throw an exception on uninitialized access.
  "-Xlint:_",               // Enable or disable specific warnings: '_' for all
  // ******* Private options *******
  "-Yconst-opt",            // Perform optimization with constant values
  "-Ywarn-dead-code",       // Warn when dead code is identified
  "-Ywarn-inaccessible",    // Warn about inaccessible types in method signatures
  "-Ywarn-unused",          // Warn when local and private vals, vars, defs, and types are unused
  "-Ywarn-value-discard"    // Warn when non-Unit expression results are unused
)


lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play"  %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play"       %% "play-mailer"        % "5.0.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

/** Needed by Activator */
fork in run := true

/******************** Extra Play Framework devSettings **********************
  *
  * You can configure extra settings for the run command in your build.sbt.
  * These settings won’t be used when you deploy your application.
  *
  */
//PlayKeys.devSettings := Seq("play.server.http.port" -> "8080")

/**
  * By default (since 2.5.0), Play will generate a router that will declare all the controllers that it routes to
  * as dependencies, allowing the controllers to be dependency injected themselves.
  *
  * When using the injected routes generator, prefixing the action with an @ symbol means a Provider of the controller
  * will be injected, instead of the controller being injected directly. This allows, for example,
  * prototype controllers, as well as an option for breaking cyclic dependencies.
  *
  * Enable the injected routes generator.
  */
routesGenerator := InjectedRoutesGenerator
