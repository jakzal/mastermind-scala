package mastermind

object Code {
  def apply(pins: List[String]): Code = new Code(pins.map(CodePeg.withName(_)))

  def apply(pins: String): Code = Code(pins.split(" ").toList)
}

class Code(val pegs: List[CodePeg.Value]) {
  def colourHits(code: Code): Int = {
    val filteredPegs = pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 != pegTuple._2).unzip
    val left = filteredPegs._1.groupBy(peg => peg).mapValues(_.size)
    val right = filteredPegs._2.groupBy(peg => peg).mapValues(_.size)

    left.foldLeft(0)((hits, pegCount) => hits + math.min(pegCount._2, right.getOrElse(pegCount._1, 0)))
  }

  def exactHits(code: Code): Int = {
    pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 == pegTuple._2).length
  }

  override def toString: String = "Code(" + pegs.foldLeft("")((s, peg) => s + peg + ", ").dropRight(2).trim + ")"
}
