package mastermind

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class RandomCodeMakerSpec extends WordSpec with Matchers {
  "RandomCodeMaker" should {
    "generate a code of given length" in {
      RandomCodeMaker(1)().length should be(1)
      RandomCodeMaker(4)().length should be(4)
    }
  }
}
