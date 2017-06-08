package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class GameController @Inject() extends Controller {
  def index = Action { implicit request =>
    Ok(Json.toJson(Map("message" -> "Welcome to Play")))
  }
}
