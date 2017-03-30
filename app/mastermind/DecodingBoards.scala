package mastermind

trait DecodingBoards {
  def add(decodingBoard: DecodingBoard)

  def load(gameUuid: GameUuid): DecodingBoard
}
