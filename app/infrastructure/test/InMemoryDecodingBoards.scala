package infrastructure.test

import mastermind.exceptions.DecodingBoardNotFoundException
import mastermind.{DecodingBoard, DecodingBoards, GameUuid}

import scala.collection.mutable.Map

class InMemoryDecodingBoards extends DecodingBoards {
  private val boards = Map.empty[GameUuid, DecodingBoard]

  override def load(gameUuid: GameUuid) = boards.getOrElse(gameUuid, {
    throw new DecodingBoardNotFoundException(gameUuid)
  })

  override def remember(decodingBoard: DecodingBoard) = boards(decodingBoard.gameUuid) = decodingBoard
}
