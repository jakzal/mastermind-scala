package mastermind

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class DecodingBoardSpec extends FlatSpec with Matchers {
  "tryCode" should "return a new guess" in {
    val gameUuid = GameUuid()
    val secretCode = Code("Red Green Blue Yellow")
    val numberOfAttempts = 6
    val board = new DecodingBoard(gameUuid, secretCode, numberOfAttempts)
    val guess = board.tryCode(Code("Blue Red Green Green"))

    guess.secretCode should have('pegs (Code("Red Green Blue Yellow").pegs))
    guess.guessCode should have('pegs (Code("Blue Red Green Green").pegs))
  }

  "lastGuess" should "return the most recent guess" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 6)

    board.tryCode(Code("Blue Red Green Green"))
    val guess = board.tryCode(Code("Blue Red Green Green"))

    board.lastGuess() should be(guess)
  }
}
