package mastermind

object Code {
  def apply(pins: List[String]): Code = new Code(pins.map(CodePeg.withName(_)))
  def apply(pins: String): Code = Code(pins.split(" ").toList)
}

class Code(val pegs: List[CodePeg.Value]) {
}
