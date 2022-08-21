package urmat.jenaliev.metrics

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions.col
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.core.SparkTest
import urmat.jenaliev.core.source.SimpleStructSource
import urmat.jenaliev.core.struct.SimpleStruct

import scala.util.{Failure, Random, Success}

final class MetricTest extends AnyWordSpecLike with Matchers with SparkTest {

  object SampleSource extends SimpleStructSource(Constants.DefaultRecordAmount)

  "NullableMetric" should {
    "return 0 on correct dataset" in {
      val nullableMetric =
        new Nullable(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              Some(Random.alphanumeric.take(10).mkString)
            )
          ),
          col("name")
        )
      nullableMetric.collect match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 0
        case Failure(_: Throwable)                   => None
      }
    }
    "return 100 on spoiled dataset" in {
      val nullableMetric =
        new Nullable(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              if (row < 100) None else Some(Random.alphanumeric.take(10).mkString)
            )
          ),
          col("name")
        )
      nullableMetric.collect match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 100
        case Failure(_: Throwable)                   => None
      }
    }
  }

  "SizeMetric" should {
    "return 0 on correct dataset" in {
      val sizeMetric = new Size(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            if (row < 100) None else Some(Random.alphanumeric.take(10).mkString)
          )
        ),
        col("name"),
        0,
        1000
      )
      sizeMetric.collect match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 0
        case Failure(_: Throwable)                   => None
      }
    }
    "return 900 on overflow dataset" in {
      val sizeMetric = new Size(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            if (row < 100) None else Some(Random.alphanumeric.take(10).mkString)
          )
        ),
        col("name"),
        0,
        100
      )
      sizeMetric.collect match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 900
        case Failure(_: Throwable)                   => None
      }
    }
  }
}
