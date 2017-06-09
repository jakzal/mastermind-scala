package mastermind

import mastermind.exceptions.DecodingBoardNotFoundException

trait DecodingBoards {
  def remember(decodingBoard: DecodingBoard)

  @throws(classOf[DecodingBoardNotFoundException])
  def load(gameUuid: GameUuid): DecodingBoard
}
