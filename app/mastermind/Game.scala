package mastermind

class Game(val decodingBoards: DecodingBoards) {
  def start(codeMaker: CodeMaker, numberOfAttempts: Int): GameUuid = {
    val board = new DecodingBoard(GameUuid(), codeMaker(), numberOfAttempts)
    decodingBoards.remember(board)
    board.gameUuid
  }

  def tryCode(gameUuid: GameUuid, code: Code) = {
    load(gameUuid).tryCode(code)
  }

  def load(gameUuid: GameUuid): DecodingBoard = {
    decodingBoards.load(gameUuid)
  }
}
