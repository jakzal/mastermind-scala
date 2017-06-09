package steps

import cucumber.api.scala.{EN, ScalaDsl}
import mastermind._
import mastermind.exceptions.DecodingBoardNotFoundException
import org.scalatest.Matchers

import scala.collection.mutable.Map

class StepDefinitions extends ScalaDsl with EN with Matchers {
  var numberOfAttempts: Int = 0
  var secretCode: Code = null
  var game: Game = new Game(new DecodingBoards {
    private val boards = Map.empty[GameUuid, DecodingBoard]

    override def load(gameUuid: GameUuid) = boards.getOrElse(gameUuid, {
      throw new DecodingBoardNotFoundException(gameUuid)
    })

    override def remember(decodingBoard: DecodingBoard) = boards(decodingBoard.gameUuid) = decodingBoard
  })
  var gameUuid: GameUuid = null

  Given("""^a decoding board of (\d+) attempts$""") { (attempts: Int) =>
    numberOfAttempts = attempts
  }

  Given("""^the code maker placed the "([^"]*)" pattern on the board$""") { (codePattern: String) =>
    secretCode = Code(codePattern)
    gameUuid = game.start(() => secretCode, numberOfAttempts)
  }

  When("""^I try to break the code with "([^"]*)"$""") { (codePattern: String) =>
    game.tryCode(gameUuid, Code(codePattern))
  }

  Then("""^the code maker should give me "([^"]*)" feedback on my guess$""") { (feedback: String) =>
    val board = game.load(gameUuid)

    board.lastGuess().exactHits() should be(feedback.count(_ == 'X'))
    board.lastGuess().colourHits() should be(feedback.count(_ == 'O'))
  }

  When("""^I try to break the code with an invalid pattern (\d+) times$""") { (times: Int) =>
    for (i <- 1 to times) game.tryCode(gameUuid, Code("Purple Purple Purple Purple"))
  }

  When("""^I break the code in the final guess$""") { () =>
    game.tryCode(gameUuid, secretCode)
  }

  Then("""^I should win the game$""") { () =>
    game.load(gameUuid).isGameWon() should be(true)
  }

  Then("""^I should loose the game$""") { () =>
    game.load(gameUuid).isGameLost() should be(true)
  }
}
