package infrastructure.filesystem

import java.io.File

import mastermind.exceptions.DecodingBoardNotFoundException
import mastermind.{Code, DecodingBoard, GameUuid}
import org.apache.commons.io.FileUtils
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class FilesystemDecodingBoardsSpec extends WordSpec with Matchers with BeforeAndAfter {
  lazy val dir = {
    val dir = File.createTempFile("FilesystemDecodingBoards", "")
    dir.delete
    dir
  }

  after {
    dir.listFiles().foreach(_.delete())
    dir.delete()
  }

  "FilesystemDecodingBoard" should {
    val decodingBoards = new FilesystemDecodingBoards(dir)

    "load the previously added decoding board" in {
      val firstBoard = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)
      val secondBoard = new DecodingBoard(GameUuid(), Code("Green", "Yellow"), 9)

      decodingBoards.add(firstBoard)
      decodingBoards.add(secondBoard)

      decodingBoards.load(firstBoard.gameUuid) should be(firstBoard)
      decodingBoards.load(secondBoard.gameUuid) should be(secondBoard)
    }

    "load previous guesses" in {
      val board = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)
      board.tryCode(Code("Red", "Red"))
      board.tryCode(Code("Red", "Blue"))
      decodingBoards.add(board)

      val loadedBoard = decodingBoards.load(board.gameUuid)

      loadedBoard should be(board)
      loadedBoard.guesses().head.guessCode should be(Code("Red", "Red"))
    }

    "throw an exception if the board does not exist" in {
      val board = new DecodingBoard(GameUuid(), Code("Red", "Blue"), 6)

      decodingBoards.add(board)

      assertThrows[DecodingBoardNotFoundException] {
        decodingBoards.load(GameUuid())
      }
    }

    "throw an exception if the game file is malformed" in {
      val gameUuid = GameUuid()
      createMalformGameFile(gameUuid)

      assertThrows[DecodingBoardNotFoundException] {
        decodingBoards.load(gameUuid)
      }
    }

    "throw an exception if the game file does not contain the board" in {
      val gameUuid = GameUuid()
      createInvalidGameFile(gameUuid)

      assertThrows[DecodingBoardNotFoundException] {
        decodingBoards.load(gameUuid)
      }
    }
  }

  private def createInvalidGameFile(gameUuid: GameUuid) = {
    FileUtils.writeStringToFile(file(gameUuid), """{"name":"Foo"}""");
  }

  private def createMalformGameFile(gameUuid: GameUuid) = {
    FileUtils.writeStringToFile(file(gameUuid), "{{{");
  }

  private def file(gameUuid: GameUuid): File = new File(dir.getCanonicalPath + "/" + gameUuid + ".json")
}
