package mastermind

import mastermind.exceptions.DecodingBoardNotFoundException
import org.scalatest.{Matchers, WordSpec}

/**
  * A contract test for the DecodingBoards trait.
  */
abstract class DecodingBoardsSpec extends WordSpec with Matchers {

  def createDecodingBoards: DecodingBoards

  "DecodingBoards" should {

    "load the previously added decoding board" in {
      val decodingBoards = createDecodingBoards
      val firstBoard = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)
      val secondBoard = new DecodingBoard(GameUuid(), Code("Green", "Yellow"), 9)

      decodingBoards.remember(firstBoard)
      decodingBoards.remember(secondBoard)

      decodingBoards.load(firstBoard.gameUuid) should be(firstBoard)
      decodingBoards.load(secondBoard.gameUuid) should be(secondBoard)
    }

    "load previous guesses" in {
      val decodingBoards = createDecodingBoards
      val board = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)
      board.tryCode(Code("Red", "Red"))
      board.tryCode(Code("Red", "Blue"))
      decodingBoards.remember(board)

      val loadedBoard = decodingBoards.load(board.gameUuid)

      loadedBoard should be(board)
      loadedBoard.guesses().length should be(2)
      loadedBoard.guesses().head.guessCode should be(Code("Red", "Red"))
    }

    "throw an exception if the board does not exist" in {
      val decodingBoards = createDecodingBoards
      val board = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)

      decodingBoards.remember(board)

      assertThrows[DecodingBoardNotFoundException] {
        decodingBoards.load(GameUuid())
      }
    }
  }
}
