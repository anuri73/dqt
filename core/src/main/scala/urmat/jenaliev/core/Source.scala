package urmat.jenaliev.core

import org.apache.spark.sql.{Dataset, SparkSession}

import scala.reflect.runtime.universe._

abstract class Source[T: TypeTag] {
  def generate(f: Int => T)(implicit spark: SparkSession): Dataset[T]
}
