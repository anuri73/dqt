package urmat.jenaliev.dqt

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions.col
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.callbacks.LogIt
import urmat.jenaliev.constraints.NotNullConstraint
import urmat.jenaliev.core.SparkTest
import urmat.jenaliev.core.source.{IntSource, SimpleStructSource}
import urmat.jenaliev.core.struct.SimpleStruct

import scala.util.Random

final class DQTTest extends AnyWordSpecLike with Matchers with SparkTest with MockitoSugar {

  object IntSource extends IntSource(Constants.DefaultRecordAmount)

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

  "DQT" should {
    "be valid on a correct dataset" in {

      import spark.implicits._

      val logIt = mock[LogIt[SimpleStruct]]

      val dqt: DQT[SimpleStruct] = new DQT(
        correctDataset,
        Seq(new NotNullConstraint(col("name"))),
        Seq(logIt)
      )
      dqt.run()

      verify(logIt, never).call(spark.emptyDataset[SimpleStruct])

    }

    "log data on invalid dataset" in {

      val logIt = mock[LogIt[SimpleStruct]]

      val dqt: DQT[SimpleStruct] = new DQT(
        nullableDataset,
        Seq(new NotNullConstraint(col("name"))),
        Seq(logIt)
      )

      dqt.run()

      verify(logIt, atLeastOnce).call(any[Dataset[SimpleStruct]])
    }
  }
}
