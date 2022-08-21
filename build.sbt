// Seq of 'wartremover' checks, causing compilation fail if found
lazy val warts = Seq(
  "-P:wartremover:traverser:org.wartremover.warts.FinalCaseClass", // All case classes should be final
  "-P:wartremover:traverser:org.wartremover.warts.Var"             // Prohibit usage of vars
)

lazy val compilerFlags = Seq(
  scalacOptions ++= Seq(
    "-deprecation", // Emit warning and location for usages of deprecated APIs
    "-encoding",
    "UTF-8",                         // Specify character encoding used by source files
    "-explaintypes",                 // Explain type errors in more detail
    "-feature",                      // Emit warning and location for usages of features that should be imported explicitly
    "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred ([_])
    "-language:higherKinds",         // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-language:reflectiveCalls",     // Allow reflective access to members of structural types
    "-unchecked",                    // Enable additional warnings where generated code depends on assumptions
    "-Xcheckinit",                   // Wrap field accessors to throw an exception on uninitialized access.
    "-Xlint:adapted-args",           // Warn if an argument list is modified to match the receiver.
    "-Xlint:delayedinit-select",     // Selecting member of DelayedInit.
    "-Xlint:doc-detached",           // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",           // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",              // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",   // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-override",       // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xlint:nullary-unit",           // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",        // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",         // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",            // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",  // A local type parameter shadows a type already in scope.
    "-Ypatmat-exhaust-depth",
    "off",             // Check every possible variant of pattern matching not regarding depth
    "-Ywarn-dead-code" // Warn when dead code is identified.
  ) ++ warts,
  Compile / doc / scalacOptions ++= Seq(
    "-no-link-warnings" // Suppress problems with Scaladoc links
  )
)

lazy val commonSettings = Defaults.coreDefaultSettings ++ compilerFlags ++ Seq(
  scalaVersion := Version.scala2v11,
  organization := "urmat.jenaliev",
  // avoid java.lang.NullPointerException at okhttp3.JavaNetAuthenticator.authenticate when downloading dependencies
  updateOptions := updateOptions.value.withGigahorse(false),
  assembly / assemblyOption := (assembly / assemblyOption).value.copy(includeScala = false),
  // assembly / mainClass := Some("ru.sberbank.bigdata.enki.ctl.Main"), // Avoid multiple main classes detected
  Test / testOptions += Tests.Argument("-oF"), // Verbose test output (for Jenkins)
  run / fork := true
)

lazy val core = (project in file("core"))
  .configs(IntegrationTest.extend(Test))
  .settings(
    parallelExecution := true,
    Defaults.itSettings,
    // ParallelExecution: By default, each test class is mapped to its own task and sbt executes tasks in parallel
    IntegrationTest / parallelExecution := false,
    IntegrationTest / test := (IntegrationTest / test).dependsOn(Test / compile).value,
    commonSettings,
    crossScalaVersions := Seq(Version.scala2v11, Version.scala2v12),
    libraryDependencies ++= Seq(
      Dependency.sparkCore(Version.spark.value) % Provided,
      Dependency.sparkSql(Version.spark.value)  % Provided,
      Dependency.scalaTest                      % "test, it"
    )
  )

lazy val metrics = (project in file("metrics"))
  .configs(IntegrationTest.extend(Test))
  .settings(
    parallelExecution := true,
    Defaults.itSettings,
    IntegrationTest / parallelExecution := false,
    IntegrationTest / test := (IntegrationTest / test).dependsOn(Test / compile).value,
    commonSettings,
    crossScalaVersions := Seq(Version.scala2v11, Version.scala2v12),
    libraryDependencies ++= Seq(
      Dependency.sparkCore(Version.spark.value) % Provided,
      Dependency.sparkSql(Version.spark.value)  % Provided,
      Dependency.scalaTest                      % "test, it"
    )
  )
  .dependsOn(core)

lazy val constraints = (project in file("constraints"))
  .configs(IntegrationTest.extend(Test))
  .settings(
    parallelExecution := true,
    Defaults.itSettings,
    IntegrationTest / parallelExecution := false,
    IntegrationTest / test := (IntegrationTest / test).dependsOn(Test / compile).value,
    commonSettings,
    crossScalaVersions := Seq(Version.scala2v11, Version.scala2v12),
    libraryDependencies ++= Seq(
      Dependency.sparkCore(Version.spark.value) % Provided,
      Dependency.sparkSql(Version.spark.value)  % Provided,
      Dependency.scalaTest                      % "test, it"
    )
  )
  .dependsOn(core, metrics)