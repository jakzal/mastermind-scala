package infrastructure.http

import mastermind.DecodingBoard
import play.api.libs.json.{JsValue, Json, Writes}

class DecodingBoardSerializer {
  def decodingBoardToJson(decodingBoard: DecodingBoard): JsValue = {
    implicit val decodingBoardWrites = new Writes[DecodingBoard] {
      def writes(decodingBoard: DecodingBoard): JsValue = Json.obj(
        "uuid" -> decodingBoard.gameUuid.toString,
        "attempts" -> decodingBoard.numberOfAttempts,
        "guesses" -> decodingBoard.guesses.map(_.guessCode.pegs)
      )
    }

    Json.toJson(decodingBoard)
  }
}
