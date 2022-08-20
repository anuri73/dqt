package urmat.jenaliev.core

import java.sql.Timestamp

import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql._
import Constants._
import scala.util.Random

abstract class SampleSource extends Source[Int] {

  override def generate(implicit spark: SparkSession): Dataset[Int] = {
    import spark.implicits._
    Seq.fill(DefaultRecordCount)(Random.nextInt).toDS()
  }
}
