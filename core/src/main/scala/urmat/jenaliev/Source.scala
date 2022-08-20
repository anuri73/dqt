package urmat.jenaliev

import org.apache.spark.sql.{Dataset, SparkSession}
import scala.reflect.runtime.universe._

abstract class Source[T: TypeTag] {
  def generate(implicit spark: SparkSession): Dataset[T]
}
