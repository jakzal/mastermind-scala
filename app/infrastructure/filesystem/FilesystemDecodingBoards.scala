package infrastructure.filesystem

import java.io.{File, IOException}

import mastermind._
import mastermind.exceptions.DecodingBoardNotFoundException
import org.apache.commons.io.FileUtils
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

class FilesystemDecodingBoards (dir: File) extends DecodingBoards {

  override def load(gameUuid: GameUuid) = {
    try {
      deserialize(loadDocument(gameUuid))
    } catch {
      case _: IOException => throw new DecodingBoardNotFoundException(gameUuid)
    }
  }

  override def remember(decodingBoard: DecodingBoard) = saveDocument(decodingBoard.gameUuid, serialize(decodingBoard))

  private def file(gameUuid: GameUuid): File = new File(dir.getCanonicalPath + "/" + gameUuid + ".json")

  private def loadDocument(gameUuid: GameUuid) = FileUtils.readFileToString(file(gameUuid))

  private def saveDocument(gameUuid: GameUuid, document: String) = FileUtils.writeStringToFile(file(gameUuid), document)

  private def deserialize(document: String) = {
    implicit val gameUuidReads: Reads[GameUuid] = (__.read[String]).map(GameUuid(_))
    implicit val codeReads: Reads[Code] = (__.read[List[String]]).map(p => Code(p.map(CodePeg.withName(_))))
    implicit val guessReads: Reads[Guess] = ((__ \ "secretCode").read[Code] and (__ \ "guessCode").read[Code])(Guess.apply _)
    implicit val decodingBoardReads: Reads[DecodingBoard] = (
      (__ \ "uuid").read[GameUuid] and
        (__ \ "secretCode").read[Code] and
        (__ \ "attempts").read[Int] and
        (__ \ "guesses").read[Vector[Guess]]
      ) (DecodingBoard.apply _)

    val json = Json.parse(document)
    json.validate[DecodingBoard] match {
      case s: JsSuccess[DecodingBoard] => s.get
      case e: JsError => throw new IOException("Failed to load the decoding board: "+e.errors.toString())
    }
  }

  private def serialize(decodingBoard: DecodingBoard) = {
    implicit val guessWrites = new Writes[Guess] {
      override def writes(guess: Guess): JsValue = Json.obj(
        "secretCode" -> guess.secretCode.pegs,
        "guessCode" -> guess.guessCode.pegs
      )
    }
    implicit val decodingBoardWrites = new Writes[DecodingBoard] {
      def writes(decodingBoard: DecodingBoard): JsValue = Json.obj(
        "uuid" -> decodingBoard.gameUuid.uuid,
        "secretCode" -> decodingBoard.secretCode.pegs,
        "attempts" -> decodingBoard.numberOfAttempts,
        "guesses" -> decodingBoard.guesses
      )
    }
    Json.toJson(decodingBoard).toString()
  }
}
