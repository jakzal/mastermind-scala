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

  "isGameWon" should "return true if the last guess was correct" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 6)

    board.tryCode(Code("Red Green Blue Yellow"))

    board.isGameWon() should be(true)
  }

  "isGameWon" should "return false if the last guess was incorrect" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 6)

    board.tryCode(Code("Red Red Red Red"))

    board.isGameWon() should be(false)
  }
}
