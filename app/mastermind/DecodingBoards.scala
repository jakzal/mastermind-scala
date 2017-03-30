package mastermind

import mastermind.exceptions.DecodingBoardNotFoundException

trait DecodingBoards {
  def add(decodingBoard: DecodingBoard)

  @throws(classOf[DecodingBoardNotFoundException])
  def load(gameUuid: GameUuid): DecodingBoard
}
