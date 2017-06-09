package mastermind

import java.util.UUID

object GameUuid {
  def apply(): GameUuid = new GameUuid(UUID.randomUUID())

  def apply(uuid: String): GameUuid = new GameUuid(UUID.fromString(uuid))
}

case class GameUuid(val uuid: UUID) {
  override def toString: String = uuid.toString
}
