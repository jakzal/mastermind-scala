package controllers

import javax.inject._

import mastermind._
import play.api.libs.json._
import play.api.mvc._

@Singleton
class GameController @Inject() (game: Game) extends Controller {
  def index = Action { implicit request =>
    Ok(Json.toJson(Map("message" -> "Welcome to Play")))
  }

  def startGame = Action { implicit request =>
    val gameUuid = game.start(RandomCodeMaker(4), 12)

    Status(201).withHeaders("Location" -> ("/games/" + gameUuid.toString))
  }
}
