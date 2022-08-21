package urmat.jenaliev.core

import org.apache.spark.sql.SparkSession

trait SparkTest {

  implicit protected val spark: SparkSession = LocalSession.localSpark

}
