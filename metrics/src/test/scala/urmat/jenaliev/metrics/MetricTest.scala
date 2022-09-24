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

  val correctDataset: Dataset[SimpleStruct] = SampleSource.generate(row =>
    SimpleStruct(
      row,
      Some(Random.alphanumeric.take(10).mkString),
      row
    )
  )

  val nullableDataset: Dataset[SimpleStruct] = SampleSource.generate(row =>
    SimpleStruct(
      row,
      if (row < 100) None else Some(Random.alphanumeric.take(10).mkString),
      row
    )
  )

  val valueOverflowDataset: Dataset[SimpleStruct] = SampleSource.generate(row =>
    SimpleStruct(
      row,
      if (row < 100) None else Some(Random.alphanumeric.take(10).mkString),
      1000000 + row
    )
  )

  "NullableMetric" should {
    "return 0 on correct dataset" in {
      val metric: Nullable[SimpleStruct] = new Nullable(col("name"))
      metric.collect(correctDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 0
        case Failure(_: Throwable)                   => None
      }
    }
    "return 100 on spoiled dataset" in {
      val metric: Nullable[SimpleStruct] = new Nullable(col("name"))
      metric.collect(nullableDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 100
        case Failure(_: Throwable)                   => None
      }
    }
  }

  "SizeMetric" should {
    "return 0 on correct dataset" in {
      val metric: Size[SimpleStruct] = new Size(col("name"), 0, 1000)
      metric.collect(correctDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 0
        case Failure(_: Throwable)                   => None
      }
    }
    "return 900 on overflow dataset" in {
      val metric: Size[SimpleStruct] = new Size(col("name"), 0, 100)
      metric.collect(correctDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 900
        case Failure(_: Throwable)                   => None
      }
    }
  }

  "ValueRangeMetric" should {
    "return 0 on correct dataset" in {
      val metric: ValueRange[SimpleStruct] = new ValueRange(col("value"), 0, 1000)
      metric.collect(correctDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 0
        case Failure(_: Throwable)                   => None
      }
    }
    "return 1000 on overflow dataset" in {
      val metric: ValueRange[SimpleStruct] = new ValueRange(col("value"), 0, 1000)
      metric.collect(valueOverflowDataset) match {
        case Success(dataset: Dataset[SimpleStruct]) => dataset.count() shouldBe 1000
        case Failure(_: Throwable)                   => None
      }
    }
  }
}
