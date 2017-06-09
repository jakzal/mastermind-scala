package mastermind

import javax.inject.Inject

class Game @Inject() (val decodingBoards: DecodingBoards) {
  def start(codeMaker: CodeMaker, numberOfAttempts: Int): GameUuid = {
    val board = new DecodingBoard(GameUuid(), codeMaker(), numberOfAttempts)
    decodingBoards.remember(board)
    board.gameUuid
  }

  def tryCode(gameUuid: GameUuid, code: Code): Unit = {
    val decodingBoard = load(gameUuid)
    decodingBoard.tryCode(code)
    decodingBoards.remember(decodingBoard)
  }

  def load(gameUuid: GameUuid): DecodingBoard = {
    decodingBoards.load(gameUuid)
  }
}
