package urmat.jenaliev.constraints

import org.apache.spark.sql.{Dataset, SparkSession}

import scala.reflect.runtime.universe._

abstract class Constraints[T: TypeTag] {
  def validate(data: Dataset[T])(implicit spark: SparkSession): Unit
}
