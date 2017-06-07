package mastermind

import mastermind.exceptions.NoAttemptsLeftException
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

    guess.secretCode should be(Code("Red Green Blue Yellow"))
    guess.guessCode should be(Code("Blue Red Green Green"))
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

  "isGameWon" should "return false if there is not guesses yet" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 6)

    board.isGameWon() should be(false)
  }

  "isGameLost" should "return false if there are any attempts left" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 2)

    board.tryCode(Code("Red Red Red Red"))

    board.isGameLost() should be(false)
  }

  "isGameLost" should "return true if there is no attempts left and code was not broken" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 2)

    board.tryCode(Code("Red Red Red Red"))
    board.tryCode(Code("Green Green Green Green"))

    board.isGameLost() should be(true)
  }

  "isGameLost" should "return false if there is no attempts left but the code was broken" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 2)

    board.tryCode(Code("Red Red Red Red"))
    board.tryCode(Code("Red Green Blue Yellow"))

    board.isGameLost() should be(false)
  }

  "tryCode" should "throw an exception if the game is won" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 2)

    board.tryCode(Code("Red Green Blue Yellow"))

    assertThrows[NoAttemptsLeftException] {
      board.tryCode(Code("Red Green Blue Yellow"))
    }
  }

  "tryCode" should "throw an exception if the game is lost" in {
    val board = new DecodingBoard(GameUuid(), Code("Red Green Blue Yellow"), 2)

    board.tryCode(Code("Red Red Red Red"))
    board.tryCode(Code("Green Green Green Green"))

    assertThrows[NoAttemptsLeftException] {
      board.tryCode(Code("Red Green Blue Yellow"))
    }
  }
}
