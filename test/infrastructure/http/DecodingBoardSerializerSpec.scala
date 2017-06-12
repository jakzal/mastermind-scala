package infrastructure.http

import mastermind.{Code, DecodingBoard, GameUuid, Guess}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class DecodingBoardSerializerSpec extends WordSpec with Matchers {
  "decodingBoardToJson" should {
    "serialize a decoding board to a json value" in {
      val gameUuid = GameUuid("2f09fdc1-1ee4-46b6-b2a9-7c895bcad326")
      val secretCode = Code("Red Green Blue Yellow")
      val guessCode = Code("Blue Purple Blue Purple")
      val guessAttempts = Vector(Guess(secretCode, guessCode))
      val board = new DecodingBoard(gameUuid, secretCode, 12, guessAttempts)

      val json = new DecodingBoardSerializer().decodingBoardToJson(board)

      (json \ "uuid").as[String] should be (gameUuid.toString)
      (json \ "attempts").as[Int] should be (12)
      (json \ "guesses")(0).as[List[String]] should be (List("Blue", "Purple", "Blue", "Purple"))
    }
  }
}
