package urmat.jenaliev.core.source

import org.apache.spark.sql._
import urmat.jenaliev.core.Source

import scala.util.Random

abstract class IntSource(val amount: Int) extends Source[Int] {

  override def generate(f: Int => Int = Random.nextInt)(implicit spark: SparkSession): Dataset[Int] = {
    import spark.implicits._

    Seq.fill(amount)(f(1)).toDS()
  }
}
