package urmat.jenaliev.core

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import Constants._

class SourceTest extends AnyWordSpecLike with Matchers with SparkTest {

  object SampleSource extends SampleSource

  "Sample data generation" should {
    s"return $DefaultRecordCount rows" in {
      SampleSource.generate.count shouldBe DefaultRecordCount
    }
  }
}
