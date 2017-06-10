package mastermind

import mastermind.exceptions.InvalidCodeLengthException
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
    "not accept zero length" in {
      assertThrows[InvalidCodeLengthException] {
        RandomCodeMaker(0)
      }
    }
    "not accept negative length" in {
      assertThrows[InvalidCodeLengthException] {
        RandomCodeMaker(-1)
      }
    }
  }
}
