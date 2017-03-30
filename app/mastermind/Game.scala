package mastermind

class Game {
  def start(codeMaker: CodeMaker, numberOfAttempts: Int): GameUuid = {
    GameUuid()
  }

  def tryCode(gameUuid: GameUuid, code: Code) = {

  }

  def load(gameUuid: GameUuid): DecodingBoard = {
    new DecodingBoard
  }
}
