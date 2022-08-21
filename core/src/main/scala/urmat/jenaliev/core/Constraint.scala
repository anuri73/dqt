package urmat.jenaliev.core

import org.apache.spark.sql.{Dataset, SparkSession}

import scala.reflect.runtime.universe._

abstract class Constraint[T: TypeTag] {
  val metric: Metric[T]

  def validate(data: Dataset[T])(implicit spark: SparkSession): Unit
}
