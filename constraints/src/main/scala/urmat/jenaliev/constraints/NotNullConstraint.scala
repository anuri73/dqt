package urmat.jenaliev.constraints

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Column, Dataset, SparkSession}
import urmat.jenaliev.constraints.exception.NullValueException
import urmat.jenaliev.metrics.Nullable

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

final class NotNullConstraint[T: TypeTag](column: Column) extends Constraints[T] {

  def validate(data: Dataset[T])(implicit spark: SparkSession): Unit = {
    val nullableMetric = new Nullable(data, column)

    nullableMetric.collect match {
      case Success(failedData)   => if (failedData.count() > 0L) throw new NullValueException
      case Failure(_: Throwable) => ()
    }
  }
}
