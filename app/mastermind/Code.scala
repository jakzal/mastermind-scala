package mastermind

object Code {
  def apply(pins: List[String]): Code = new Code(pins.map(CodePeg.withName(_)))

  def apply(pins: String): Code = Code(pins.split(" ").toList)
}

class Code(val pegs: List[CodePeg.Value]) {
  def colourHits(code: Code): Int = {
    def removeExactHits(pegs: List[CodePeg.Value]): (List[CodePeg.Value], List[CodePeg.Value]) = {
      pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 != pegTuple._2).unzip
    }

    def countColours(pegs: List[CodePeg.Value]): Map[CodePeg.Value, Int] = {
      pegs.groupBy(peg => peg).mapValues(_.size)
    }

    def calculateHits(left: Map[CodePeg.Value, Int], right: Map[CodePeg.Value, Int]): Int = {
      left.foldLeft(0)((hits, pegCount) => hits + math.min(pegCount._2, right.getOrElse(pegCount._1, 0)))
    }

    val filteredPegs = removeExactHits(pegs)

    calculateHits(countColours(filteredPegs._1), countColours(filteredPegs._2))
  }

  def exactHits(code: Code): Int = {
    pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 == pegTuple._2).length
  }

  override def toString: String = "Code(" + pegs.foldLeft("")((s, peg) => s + peg + ", ").dropRight(2).trim + ")"
}
