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

    "be considered equal if the string uuid is the same" in {
      GameUuid("1070460d-60db-4e22-a0df-ce8512a251dd") should be (GameUuid("1070460d-60db-4e22-a0df-ce8512a251dd"))
    }
  }
}
