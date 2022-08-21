package urmat.jenaliev.dqt

import org.apache.spark.sql.{Dataset, SparkSession}
import urmat.jenaliev.core.{Callback, Constraint}

import scala.reflect.runtime.universe._
import scala.util.{Failure, Success}

final class DQT[T: TypeTag](
  dataset: Dataset[T],
  constraints: Seq[Constraint[T]],
  callbacks: Seq[Callback[T]]
)(implicit spark: SparkSession) {

  def run(): Unit = constraints.map { constraint =>
    constraint.metric.collect(dataset) match {
      case Success(failedData) =>
        if (failedData.count() > 0L) {
          callbacks.map(callback => callback.call(failedData))
        }
      case Failure(_: Throwable) =>
    }
  }

}
