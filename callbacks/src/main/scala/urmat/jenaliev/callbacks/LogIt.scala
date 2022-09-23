package urmat.jenaliev.callbacks

import com.typesafe.scalalogging._
import org.apache.spark.sql.Dataset
import org.slf4j.LoggerFactory
import urmat.jenaliev.core.Callback

import scala.reflect.runtime.universe._

class LogIt[T: TypeTag] extends Callback[T] {

  private val logger = Logger(LoggerFactory.getLogger(this.getClass))

  def call(invalidData: Dataset[T]): Unit = {
    if (invalidData.count() > 0L) {
      logger.warn("Invalid data found")
    }
    for (row <- invalidData.rdd.collect)
      logger.warn(s"$row")
  }
}
