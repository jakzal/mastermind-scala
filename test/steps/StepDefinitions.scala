package steps

import cucumber.api.PendingException
import cucumber.api.scala.{EN, ScalaDsl}
import mastermind._
import collection.mutable.Map

class StepDefinitions extends ScalaDsl with EN {
  var numberOfAttempts: Int = 0
  var game: Game = new Game(new DecodingBoards {
    private val boards = Map.empty[GameUuid, DecodingBoard]

    override def load(gameUuid: GameUuid) = boards.getOrElse(gameUuid, {throw new Exception(s"Unexpected game uuid: `$gameUuid`.")})

    override def add(decodingBoard: DecodingBoard) = boards(decodingBoard.gameUuid) = decodingBoard
  })
  var gameUuid: GameUuid = null

  Given("""^a decoding board of (\d+) attempts$""") { (attempts: Int) =>
    numberOfAttempts = attempts
  }

  Given("""^the code maker placed the "([^"]*)" pattern on the board$""") { (codePattern: String) =>
    gameUuid = game.start(() => Code(codePattern), numberOfAttempts)
  }

  When("""^I try to break the code with "([^"]*)"$""") { (codePattern: String) =>
    game.tryCode(gameUuid, Code(codePattern))
  }

  Then("""^the code maker should give me "([^"]*)" feedback on my guess$""") { (feedback: String) =>
    val board = game.load(gameUuid)

//    board.lastGuess().exactHits() should be (feedback.count(_ == 'X'))
//    board.lastGuess().colourHits() should be (feedback.count(_ == 'O'))
    throw new PendingException()
  }

  When("""^I try to break the code with an invalid pattern (\d+) times$""") { (arg0: Int) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  When("""^I break the code in the final guess$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Then("""^I should win the game$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Then("""^I should loose the game$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }
}
