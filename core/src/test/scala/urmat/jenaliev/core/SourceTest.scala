package urmat.jenaliev.core

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import urmat.jenaliev.core.Constants._
import urmat.jenaliev.core.source.IntSource

class SourceTest extends AnyWordSpecLike with Matchers with SparkTest {

  object IntSource extends IntSource(Constants.DefaultRecordAmount)

  "Sample data generation" should {
    s"return $DefaultRecordAmount rows" in {
      IntSource.generate().count shouldBe DefaultRecordAmount
    }
  }
}
