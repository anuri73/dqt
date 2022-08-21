package urmat.jenaliev.dqt

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions.col
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.callbacks.LogIt
import urmat.jenaliev.constraints.NotNullConstraint
import urmat.jenaliev.core.source.{IntSource, SimpleStructSource}
import urmat.jenaliev.core.struct.SimpleStruct
import urmat.jenaliev.core.{Callback, Constraint, SparkTest}
import org.mockito.MockitoSugar
import scala.util.Random

final class DQTTest extends AnyWordSpecLike with Matchers with SparkTest with MockitoSugar {

  object IntSource extends IntSource(Constants.DefaultRecordAmount)

  object SampleSource extends SimpleStructSource(Constants.DefaultRecordAmount)

  val correctDataset: Dataset[SimpleStruct] = SampleSource.generate(row =>
    SimpleStruct(
      row,
      Some(Random.alphanumeric.take(10).mkString)
    )
  )

  val nullableDataset: Dataset[SimpleStruct] = SampleSource.generate(row =>
    SimpleStruct(
      row,
      if (row < 100) None else Some(Random.alphanumeric.take(10).mkString)
    )
  )

  "DQT" should {
    "call LogIt callback" in {

      val logIt = mock[LogIt[SimpleStruct]]
      verify(logIt, times(1))

      val dqt: DQT[SimpleStruct] = new DQT(
        correctDataset,
        Seq[Constraint[SimpleStruct]](new NotNullConstraint(col("name"))),
        Seq[Callback[SimpleStruct]](logIt)
      )
      dqt.run()

    }
  }
}
