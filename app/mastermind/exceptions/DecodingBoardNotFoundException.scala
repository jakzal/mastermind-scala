package mastermind.exceptions

import mastermind.GameUuid

case class DecodingBoardNotFoundException(gameUuid: GameUuid) extends Exception(s"Decoding board `$gameUuid` not found.") {
}
