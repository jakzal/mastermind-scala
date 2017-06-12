package controllers

import com.google.inject.AbstractModule
import infrastructure.test.InMemoryDecodingBoards
import mastermind.DecodingBoards
import org.scalatest.TestData
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json._
import play.api.mvc.Result
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.Future

class GameControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  implicit override def newAppForTest(testData: TestData): Application =
    new GuiceApplicationBuilder()
      .overrides(
        new AbstractModule {
          override def configure(): Unit = bind(classOf[DecodingBoards]).to(classOf[InMemoryDecodingBoards])
        }
      )
      .build()

  def get(path: String): Future[Result] = route(app, FakeRequest(GET, path).withHeaders("Host" -> "localhost")).get
  def post(path: String): Future[Result] = route(app, FakeRequest(POST, path).withHeaders("Host" -> "localhost")).get

  "GameController GET" should {

    "introduce itself" in {
      val index = get("/")

      status(index) mustBe OK
      contentType(index) mustBe Some("application/json")
      (contentAsJson(index) \ "message").get mustBe JsString("Welcome to Play")
    }

    "start a new game" in {
      val startGame = post("/games")

      status(startGame) mustBe CREATED
      header("Location", startGame).getOrElse("") must fullyMatch regex """/games/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"""
    }

    "load an existing game" in {
      val gameResource = header("Location", post("/games")).get
      val uuid = gameResource.replace("/games/", "")
      val game = get(gameResource)

      status(game) mustBe OK
      contentType(game) mustBe Some("application/json")
      (contentAsJson(game) \ "uuid").get mustBe JsString(uuid)
    }
  }
}
