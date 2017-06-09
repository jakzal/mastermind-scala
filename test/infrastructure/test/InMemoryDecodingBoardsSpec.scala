package infrastructure.test

import mastermind.{DecodingBoards, DecodingBoardsSpec}
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InMemoryDecodingBoardsSpec extends DecodingBoardsSpec with BeforeAndAfter {

  def createDecodingBoards: DecodingBoards = new InMemoryDecodingBoards()
}
