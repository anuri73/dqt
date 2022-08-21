package urmat.jenaliev.constraints

import org.apache.spark.sql.{Column, Dataset, SparkSession}
import urmat.jenaliev.constraints.exception.SizeException
import urmat.jenaliev.metrics.Size

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

final class SizeConstraint[T: TypeTag](column: Column, min: Int, max: Int) extends Constraints[T] {

  def validate(data: Dataset[T])(implicit spark: SparkSession): Unit = {
    val sizeMetric = new Size(data, column, min, max)

    sizeMetric.collect match {
      case Success(failedData)   => if (failedData.count() > 0L) throw new SizeException
      case Failure(_: Throwable) => ()
    }
  }
}
