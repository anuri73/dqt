package urmat.jenaliev.core

import org.apache.spark.sql.{Dataset, SparkSession}

import scala.util.Try

trait Metric[T] {
  val entity: Entity.Value

  def collect(dataset: Dataset[T])(implicit spark: SparkSession): Try[Dataset[T]]
}
