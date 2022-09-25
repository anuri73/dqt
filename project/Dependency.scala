import sbt._

object Dependency {
  def circeCore(circeVersion: String): ModuleID     = "io.circe"         %% "circe-core"     % circeVersion
  def circeGeneric(circeVersion: String): ModuleID  = "io.circe"         %% "circe-generic"  % circeVersion
  def circeLiteral(circeVersion: String): ModuleID  = "io.circe"         %% "circe-literal"  % circeVersion
  def circeParser(circeVersion: String): ModuleID   = "io.circe"         %% "circe-parser"   % circeVersion
  def http4s(name: String): ModuleID                = "org.http4s"       %% s"http4s-$name"  % Version.http4s
  def sparkCatalyst(sparkVersion: String): ModuleID = "org.apache.spark" %% "spark-catalyst" % sparkVersion
  def sparkCore(sparkVersion: String): ModuleID     = "org.apache.spark" %% "spark-core"     % sparkVersion
  def sparkHive(sparkVersion: String): ModuleID     = "org.apache.spark" %% "spark-hive"     % sparkVersion
  def sparkSql(sparkVersion: String): ModuleID      = "org.apache.spark" %% "spark-sql"      % sparkVersion

  val cats            = "org.typelevel"                %% "cats-core"        % Version.cats
  val catsEffect      = "org.typelevel"                %% "cats-effect"      % Version.catsEffect
  val circeYaml       = "io.circe"                     %% "circe-yaml"       % Version.circeYaml
  val decline         = "com.monovore"                 %% "decline"          % Version.decline
  val declineRefined  = "com.monovore"                 %% "decline-refined"  % Version.decline
  val enumeratum      = "com.beachape"                 %% "enumeratum"       % Version.enumeratum
  val enumeratumCirce = "com.beachape"                 %% "enumeratum-circe" % Version.enumeratumCirce
  val graphvizJava    = "guru.nidi"                     % "graphviz-java"    % Version.graphviz
  val hadoopCommon    = "org.apache.hadoop"             % "hadoop-common"    % Version.hadoopTest
  val hadoopHDFS      = "org.apache.hadoop"             % "hadoop-hdfs"      % Version.hadoopTest
  val hiveMetastore   = "org.apache.hive"               % "hive-metastore"   % Version.hiveMetaStore
  val logBack         = "ch.qos.logback"                % "logback-classic"  % Version.logBack
  val mockitoScala    = "org.mockito"                  %% "mockito-scala"    % Version.mockitoScala
  val poi             = "org.apache.poi"                % "poi"              % Version.poi
  val poiOoxml        = "org.apache.poi"                % "poi-ooxml"        % Version.poi
  val pureConfig      = "com.github.pureconfig"        %% "pureconfig"       % Version.pureConfig
  val refined         = "eu.timepit"                   %% "refined"          % Version.refined
  val scalaGraphCore  = "org.scala-graph"              %% "graph-core"       % Version.scalaGraphCore
  val scalaGraphDot   = "org.scala-graph"              %% "graph-dot"        % Version.scalaGraphDot
  val scalaMock       = "org.scalamock"                %% "scalamock"        % Version.scalaMock
  val scalaTest       = "org.scalatest"                %% "scalatest"        % Version.scalaTest
  val scalaXml        = "org.scala-lang.modules"       %% "scala-xml"        % Version.scalaXml
  val scalajHttp      = "org.scalaj"                   %% "scalaj-http"      % Version.scalajHttp
  val scallop         = "org.rogach"                   %% "scallop"          % Version.scallop
  val sttpClientCirce = "com.softwaremill.sttp.client" %% "circe"            % Version.sttpClient
  val sttpClientCore  = "com.softwaremill.sttp.client" %% "core"             % Version.sttpClient
  val typesafeLogging = "com.typesafe.scala-logging"   %% "scala-logging"    % Version.typesafeLogging
  val zio             = "dev.zio"                      %% "zio"              % Version.zio

  // Compile-time only
  val macroParadise = ("org.scalamacros" % "paradise" % Version.macroParadise).cross(CrossVersion.patch)
}
