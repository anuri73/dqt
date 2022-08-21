package urmat.jenaliev.core

import org.apache.spark.sql.Dataset

trait Callback[T] {
  def call(invalidData: Dataset[T]): Unit
}
