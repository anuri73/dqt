import sbt.Keys._
import sbt._

object Version {
  // cannot upgrade to 2.11.12 as `loadFiles()` in `ILoop` was removed in scala 2.11.12 which breaks Apache Spark
  val scala2v11 = "2.11.8"
  val scala2v12 = "2.12.10"
  val scala2v13 = "2.13.9"

  val spark = Def.setting {
    scalaVersion.value match {
      case `scala2v11` => "2.2.0"
      case `scala2v12` => "2.4.4"
      case `scala2v13` => "3.2.2"
    }
  }

  // circe 0.11.1 is incompatible with enki-plugins
  val circe = Def.setting {
    scalaVersion.value match {
      case `scala2v11` => "0.11.1"
      case `scala2v12` => "0.13.0"
      case `scala2v13` => "0.13.0"
    }
  }

  val cats            = "2.0.0"
  val catsEffect      = "2.0.0"
  val circeYaml       = "0.10.0"
  val decline         = "1.0.0"
  val enumeratum      = "1.5.15"
  val enumeratumCirce = "1.5.23"
  val graphviz        = "0.14.1"
  val hadoopTest      = "2.6.5"
  val hiveMetaStore   = "1.2.1"
  val http4s          = "0.20.23"
  val logBack         = "1.2.11"
  val macroParadise   = "2.1.0"
  val mockitoScala    = "1.11.4"
  val poi             = "4.1.1"
  val pureConfig      = "0.12.3"
  val refined         = "0.9.12"
  val scalaGraphCore  = "1.12.5"
  val scalaGraphDot   = "1.11.5"
  val scalaMock       = "4.4.0"
  val scalaTest       = "3.3.0-SNAP3"
  val scalaXml        = "1.2.0"
  val scalajHttp      = "2.4.2"
  val scallop         = "3.4.0"
  val sttpClient      = "2.2.1"
  val typesafeLogging = "3.9.2"
  val zio             = "1.0.0-RC21-2"
}
