package mastermind

import java.util.UUID

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class GameUuidSpec extends WordSpec with Matchers {
  "GameUuid" should {
    "have a string representation" in {
      val uuid = UUID.randomUUID()
      val gameUuid = new GameUuid(uuid)

      gameUuid.toString should be(uuid.toString())
    }

    "be created from string" in {
      val uuid = UUID.randomUUID().toString
      val gameUuid = GameUuid(uuid)

      gameUuid.toString should be(uuid)
    }

    "be randomly created" in {
      GameUuid().toString should fullyMatch regex """^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"""
    }
  }
}
