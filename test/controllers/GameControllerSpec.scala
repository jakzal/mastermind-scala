package controllers

import org.scalatestplus.play._
import play.api.libs.json.JsString
import play.api.mvc.Result
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future

class GameControllerSpec extends PlaySpec with OneAppPerTest {

  def get(path: String): Future[Result] = route(app, FakeRequest(GET, path).withHeaders("Host" -> "localhost")).get

  "GameController GET" should {

    "introduce itself" in {
      val index = get("/")

      status(index) mustBe OK
      contentType(index) mustBe Some("application/json")
      (contentAsJson(index) \ "message").get mustBe JsString("Welcome to Play")
    }
  }
}
