import sbt._

object Dependency {
  def sparkCore(sparkVersion: String): ModuleID     = "org.apache.spark" %% "spark-core"     % sparkVersion
  def sparkSql(sparkVersion: String): ModuleID      = "org.apache.spark" %% "spark-sql"      % sparkVersion
  def sparkHive(sparkVersion: String): ModuleID     = "org.apache.spark" %% "spark-hive"     % sparkVersion
  def sparkCatalyst(sparkVersion: String): ModuleID = "org.apache.spark" %% "spark-catalyst" % sparkVersion
  def circeCore(circeVersion: String): ModuleID     = "io.circe"         %% "circe-core"     % circeVersion
  def circeGeneric(circeVersion: String): ModuleID  = "io.circe"         %% "circe-generic"  % circeVersion
  def circeParser(circeVersion: String): ModuleID   = "io.circe"         %% "circe-parser"   % circeVersion
  def circeLiteral(circeVersion: String): ModuleID  = "io.circe"         %% "circe-literal"  % circeVersion

  def http4s(name: String): ModuleID = "org.http4s" %% s"http4s-$name" % Version.http4s

  val hiveMetastore   = "org.apache.hive"               % "hive-metastore"   % Version.hiveMetaStore
  val scalaTest       = "org.scalatest"                %% "scalatest"        % Version.scalaTest
  val mockitoScala    = "org.mockito"                  %% "mockito-scala"    % Version.mockitoScala
  val scalaXml        = "org.scala-lang.modules"       %% "scala-xml"        % Version.scalaXml
  val typesafeLogging = "com.typesafe.scala-logging"   %% "scala-logging"    % Version.typesafeLogging
  val logBack         = "ch.qos.logback"                % "logback-classic"  % Version.logBack
  val scallop         = "org.rogach"                   %% "scallop"          % Version.scallop
  val scalajHttp      = "org.scalaj"                   %% "scalaj-http"      % Version.scalajHttp
  val pureConfig      = "com.github.pureconfig"        %% "pureconfig"       % Version.pureConfig
  val scalaGraphCore  = "org.scala-graph"              %% "graph-core"       % Version.scalaGraphCore
  val scalaGraphDot   = "org.scala-graph"              %% "graph-dot"        % Version.scalaGraphDot
  val enumeratum      = "com.beachape"                 %% "enumeratum"       % Version.enumeratum
  val enumeratumCirce = "com.beachape"                 %% "enumeratum-circe" % Version.enumeratumCirce
  val zio             = "dev.zio"                      %% "zio"              % Version.zio
  val sttpClientCore  = "com.softwaremill.sttp.client" %% "core"             % Version.sttpClient
  val sttpClientCirce = "com.softwaremill.sttp.client" %% "circe"            % Version.sttpClient
  val catsEffect      = "org.typelevel"                %% "cats-effect"      % Version.catsEffect
  val decline         = "com.monovore"                 %% "decline"          % Version.decline
  val declineRefined  = "com.monovore"                 %% "decline-refined"  % Version.decline
  val circeYaml       = "io.circe"                     %% "circe-yaml"       % Version.circeYaml
  val poi             = "org.apache.poi"                % "poi"              % Version.poi
  val poiOoxml        = "org.apache.poi"                % "poi-ooxml"        % Version.poi
  val hadoopHDFS      = "org.apache.hadoop"             % "hadoop-hdfs"      % Version.hadoopTest
  val hadoopCommon    = "org.apache.hadoop"             % "hadoop-common"    % Version.hadoopTest
  val graphvizJava    = "guru.nidi"                     % "graphviz-java"    % Version.graphviz
  val cats            = "org.typelevel"                %% "cats-core"        % Version.cats
  val scalaMock       = "org.scalamock"                %% "scalamock"        % Version.scalaMock
  val refined         = "eu.timepit"                   %% "refined"          % Version.refined

  // Compile-time only
  val macroParadise = ("org.scalamacros" % "paradise" % Version.macroParadise).cross(CrossVersion.patch)
}
