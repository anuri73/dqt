package urmat.jenaliev.core

import java.nio.file.{Files, Path}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object LocalSession {
  lazy val warehouseDir: Path = Files.createTempDirectory("spark-warehouse")

  def localSpark: SparkSession = {
    Logger.getLogger("akka").setLevel(Level.WARN)

    SparkSession
      .builder()
      .appName("test")
      .master("local")
      .config("spark.sql.shuffle.partitions", 4)
      .config("spark.sql.warehouse.dir", warehouseDir.toUri.toString)
      .getOrCreate()
  }
}
