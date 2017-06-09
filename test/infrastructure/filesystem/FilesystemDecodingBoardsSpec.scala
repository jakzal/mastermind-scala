package infrastructure.filesystem

import java.io.File

import mastermind.exceptions.DecodingBoardNotFoundException
import mastermind.{DecodingBoards, DecodingBoardsSpec, GameUuid}
import org.apache.commons.io.FileUtils
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FilesystemDecodingBoardsSpec extends DecodingBoardsSpec with BeforeAndAfter {
  lazy val dir = {
    val dir = File.createTempFile("FilesystemDecodingBoards", "")
    dir.delete
    dir
  }

  after {
    dir.listFiles().foreach(_.delete())
    dir.delete()
  }

  def createDecodingBoards: DecodingBoards = new FilesystemDecodingBoards(dir)

  "FilesystemDecodingBoards" should {

    "throw an exception if the game file is malformed" in {
      val gameUuid = GameUuid()
      createMalformGameFile(gameUuid)

      assertThrows[DecodingBoardNotFoundException] {
        createDecodingBoards.load(gameUuid)
      }
    }

    "throw an exception if the game file does not contain the board" in {
      val gameUuid = GameUuid()
      createInvalidGameFile(gameUuid)

      assertThrows[DecodingBoardNotFoundException] {
        createDecodingBoards.load(gameUuid)
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
