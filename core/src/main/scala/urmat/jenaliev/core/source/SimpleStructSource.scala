package urmat.jenaliev.core.source

import org.apache.spark.sql._
import urmat.jenaliev.core.Source
import urmat.jenaliev.core.struct.SimpleStruct

abstract class SimpleStructSource(amount: Int) extends Source[SimpleStruct] {

  override def generate(f: Int => SimpleStruct)(implicit spark: SparkSession): Dataset[SimpleStruct] = {
    import spark.implicits._

    val sample = Seq.tabulate(amount)(f)

    spark.sparkContext.parallelize(sample).toDS().as[SimpleStruct]
  }
}
