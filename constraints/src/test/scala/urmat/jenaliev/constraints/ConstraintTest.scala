package urmat.jenaliev.constraints

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.constraints.exception.NullValueException
import urmat.jenaliev.core.SparkTest
import urmat.jenaliev.core.source.SimpleStructSource
import urmat.jenaliev.core.struct.SimpleStruct

import scala.util.Random

final class ConstraintTest extends AnyWordSpecLike with Matchers with SparkTest {
  object SampleSource extends SimpleStructSource(Constants.DefaultRecordAmount)

  "NullableMetric" should {
    "should be valid on correct dataset" in {

      val constraint = new NotNullConstraints[SimpleStruct]

      constraint.validate(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            Some(Random.alphanumeric.take(10).mkString)
          )
        )
      )
    }
    "should fail on invalid dataset" in {

      val constraint = new NotNullConstraints[SimpleStruct]

      assertThrows[NullValueException] {
        constraint.validate(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              if (row < 100) None else Some(Random.alphanumeric.take(10).mkString)
            )
          )
        )
      }
    }
  }
}
