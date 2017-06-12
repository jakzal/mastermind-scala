package controllers

import javax.inject._

import infrastructure.http.DecodingBoardSerializer
import mastermind._
import play.api.libs.json._
import play.api.mvc._

@Singleton
class GameController @Inject() (game: Game, serializer: DecodingBoardSerializer) extends Controller {
  def index = Action { implicit request =>
    Ok(Json.toJson(Map("message" -> "Welcome to Play")))
  }

  def startGame = Action { implicit request =>
    val gameUuid = game.start(RandomCodeMaker(4), 12)

    Status(201).withHeaders("Location" -> (routes.GameController.getGame(gameUuid.toString).url))
  }

  def getGame(gameUuid: String) = Action { implicit request =>
    val board = game.load(GameUuid(gameUuid))

    Ok(serializer.decodingBoardToJson(board))
  }
}
