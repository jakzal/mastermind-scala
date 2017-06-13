package infrastructure.http

import infrastructure.http.exceptions.InvalidGuessCodeException
import mastermind.{Code, CodePeg, DecodingBoard}
import play.api.libs.json._

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

  def jsonToGuessCode(json: JsValue): Code = {
    implicit val codeReader: Reads[Code] = (__ \ "guess")
      .read[List[String]]
      .map(p => Code(p.map(CodePeg.withName(_))))

    json.validate[Code].fold(
      errors => throw new InvalidGuessCodeException(),
      code => code
    )
  }
}
