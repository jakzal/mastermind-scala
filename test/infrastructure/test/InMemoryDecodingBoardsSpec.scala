package infrastructure.test

import mastermind.{DecodingBoards, DecodingBoardsSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InMemoryDecodingBoardsSpec extends DecodingBoardsSpec {
  def createDecodingBoards: DecodingBoards = new InMemoryDecodingBoards()
}
