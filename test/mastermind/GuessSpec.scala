package mastermind

import mastermind.exceptions.IncompatibleCodeLengthException
import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GuessSpec extends WordSpec with Matchers with MockFactory {
  "Guess" should {
    "reject guess code that is shorter than the secret code" in {
      val secretCode = code
      val guessCode = code

      (secretCode.length _).when() returns 4
      (guessCode.length _).when() returns 3

      assertThrows[IncompatibleCodeLengthException] {
        new Guess(secretCode, guessCode)
      }
    }

    "reject guess code that is longer than the secret code" in {
      val secretCode = code
      val guessCode = code

      (secretCode.length _).when() returns 4
      (guessCode.length _).when() returns 5

      assertThrows[IncompatibleCodeLengthException] {
        new Guess(secretCode, guessCode)
      }
    }
  }

  "exactHits" should {
    "return exact hits for secret and guess codes" in {
      val secretCode = code
      val guessCode = code

      (secretCode.exactHits _).when(guessCode) returns (2)

      val guess = new Guess(secretCode, guessCode)

      guess.exactHits() should be(2)
    }
  }

  "colourHits" should {
    "return colour only hits for secret and guess codes" in {
      val secretCode = code
      val guessCode = code

      (secretCode.colourHits _).when(guessCode) returns (3)

      val guess = new Guess(secretCode, guessCode)

      guess.colourHits() should be(3)
    }
  }

  "isCodeBroken" should {
    "return true if the last guess code matches the secret code" in {
      val guess = new Guess(Code("Red"), Code("Red"))

      guess.isCodeBroken() should be(true)
    }

    "return false if the last guess code does not match the secret code" in {
      val guess = new Guess(Code("Red"), Code("Blue"))

      guess.isCodeBroken() should be(false)
    }
  }

  private def code: Code = {
    // to prevent the mocking framework from calling Code's constructor with an empty list
    class CodeStub extends Code(List(CodePeg.Red))

    stub[CodeStub]
  }
}
