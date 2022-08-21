package urmat.jenaliev.metrics

import org.apache.spark.sql.{Dataset, SparkSession}

import scala.util.Try

trait Metric[T] {
  val entity: Entity.Value
  def collect(implicit spark: SparkSession): Try[Dataset[T]]
}
