package urmat.jenaliev.metrics

import org.apache.spark.sql.{Column, Dataset, SparkSession}
import urmat.jenaliev.metrics.Entity._

import scala.reflect.runtime.universe._
import scala.util.Try

final class Nullable[T: TypeTag](dataset: Dataset[T], column: Column) extends Metric[T] {

  val entity: Entity = Column

  def collect(implicit spark: SparkSession): Try[Dataset[T]] = Try {
    require(dataset.columns.contains(column.toString()), s"Missing column $column")

    dataset.filter(column.isNull)
  }
}
