package mastermind

import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class GameSpec extends FlatSpec with Matchers with MockFactory {
  "start" should "store a new board" in {
    val decodingBoards = stub[DecodingBoards]
    val game = new Game(decodingBoards)
    val secretCode = Code("Red Green")
    val numberOfAttempts = 12

    val gameUuid = game.start(() => secretCode, numberOfAttempts)

    (decodingBoards.add _).verify(where { (decodingBoard: DecodingBoard) =>
      decodingBoard.gameUuid == gameUuid && decodingBoard.numberOfAttempts == numberOfAttempts
    }) once
  }

  "load" should "load an existing game board" in {
    val gameUuid = GameUuid()
    val decodingBoard = stub[DecodingBoard]
    val decodingBoards = stub[DecodingBoards]
    (decodingBoards.load _).when(gameUuid).returns(decodingBoard)

    val game = new Game(decodingBoards)

    game.load(gameUuid) should be (decodingBoard)
  }

  "tryCode" should "attempt to make a guess" in {
    val gameUuid = GameUuid()
    val decodingBoard = stub[DecodingBoard]
    val decodingBoards = stub[DecodingBoards]
    val code = Code("Red")
    (decodingBoards.load _).when(gameUuid).returns(decodingBoard)

    val game = new Game(decodingBoards)
    game.tryCode(gameUuid, code)

    (decodingBoard.tryCode _).verify(code)
  }
}
