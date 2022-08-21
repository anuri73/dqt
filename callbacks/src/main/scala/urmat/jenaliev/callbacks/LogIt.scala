package urmat.jenaliev.callbacks

import org.apache.spark.sql.Dataset
import urmat.jenaliev.core.Callback
import scala.reflect.runtime.universe._

class LogIt[T: TypeTag] extends Callback[T] {

  def call(invalidData: Dataset[T]): Unit = {
    // Let's think we log invalid data here
  }

}
