package urmat.jenaliev.constraints

import org.apache.spark.sql.functions.col
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.constraints.exception.{NullValueException, SizeException, ValueRangeException}
import urmat.jenaliev.core.SparkTest
import urmat.jenaliev.core.source.SimpleStructSource
import urmat.jenaliev.core.struct.SimpleStruct

import scala.util.Random

final class ConstraintTest extends AnyWordSpecLike with Matchers with SparkTest {
  object SampleSource extends SimpleStructSource(Constants.DefaultRecordAmount)

  "NullableConstraint" should {
    "should be valid on correct dataset" in {

      val constraint = new NotNullConstraint[SimpleStruct](col("name"))

      constraint.validate(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            Some(Random.alphanumeric.take(10).mkString),
            row
          )
        )
      )
    }
    "should fail on invalid dataset" in {

      val constraint = new NotNullConstraint[SimpleStruct](col("name"))

      assertThrows[NullValueException] {
        constraint.validate(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              if (row < 100) None else Some(Random.alphanumeric.take(10).mkString),
              row
            )
          )
        )
      }
    }
  }

  "SizeConstraint" should {
    "should be valid on correct dataset" in {

      val constraint = new SizeConstraint[SimpleStruct](col("name"), 0, 1000)

      constraint.validate(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            Some(Random.alphanumeric.take(10).mkString),
            row
          )
        )
      )
    }
    "should fail on invalid dataset" in {

      val constraint = new SizeConstraint[SimpleStruct](col("name"), 0, 900)

      assertThrows[SizeException] {
        constraint.validate(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              Some(Random.alphanumeric.take(10).mkString),
              row
            )
          )
        )
      }
    }
  }

  "ValueRangeConstraint" should {
    "should be valid on correct dataset" in {

      val constraint = new ValueRangeConstraint[SimpleStruct](col("value"), 0, 1000)

      constraint.validate(
        SampleSource.generate(row =>
          SimpleStruct(
            row,
            Some(Random.alphanumeric.take(10).mkString),
            row
          )
        )
      )
    }
    "should fail on invalid dataset" in {

      val constraint = new ValueRangeConstraint[SimpleStruct](col("value"), 0, 1000)

      assertThrows[ValueRangeException] {
        constraint.validate(
          SampleSource.generate(row =>
            SimpleStruct(
              row,
              Some(Random.alphanumeric.take(10).mkString),
              1000000 + row
            )
          )
        )
      }
    }
  }
}
