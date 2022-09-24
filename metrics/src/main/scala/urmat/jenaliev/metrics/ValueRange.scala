package urmat.jenaliev.metrics

import org.apache.spark.sql.{Column, Dataset, SparkSession}
import urmat.jenaliev.core.Entity._
import urmat.jenaliev.core.Metric

import scala.reflect.runtime.universe._
import scala.util.Try

final class ValueRange[T: TypeTag](column: Column, min: Int, max: Int) extends Metric[T] {

  val entity: Entity = Column

  def collect(dataset: Dataset[T])(implicit spark: SparkSession): Try[Dataset[T]] = Try {
    require(dataset.columns.contains(column.toString()), s"Missing column $column")

    dataset.filter(
      column > max || column < min
    )
  }
}
