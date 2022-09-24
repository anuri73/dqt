package urmat.jenaliev.constraints

import org.apache.spark.sql.{Column, Dataset, SparkSession}
import urmat.jenaliev.constraints.exception.ValueRangeException
import urmat.jenaliev.core.Constraint
import urmat.jenaliev.metrics.ValueRange

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

final class ValueRangeConstraint[T: TypeTag](column: Column, min: Int, max: Int) extends Constraint[T] {

  override val metric = new ValueRange(column, min, max)

  def validate(data: Dataset[T])(implicit spark: SparkSession): Unit =
    metric.collect(data) match {
      case Success(failedData)   => if (failedData.count() > 0L) throw new ValueRangeException
      case Failure(_: Throwable) => ()
    }
}
