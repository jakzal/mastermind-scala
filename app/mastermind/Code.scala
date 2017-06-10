package mastermind

object Code {
  def apply(pins: String): Code = Code(pins.split(" "): _*)

  def apply(pins: String*): Code = new Code(pins.toList.map(CodePeg.withName(_)))
}

case class Code(val pegs: List[CodePeg.Value]) {
  def colourHits(code: Code): Int = {
    def diff(code: Code): (List[CodePeg.Value], List[CodePeg.Value]) = {
      pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 != pegTuple._2).unzip
    }

    def countColours(pegs: List[CodePeg.Value]): Map[CodePeg.Value, Int] = {
      pegs.groupBy(peg => peg).mapValues(_.size)
    }

    def calculateHits(left: Map[CodePeg.Value, Int], right: Map[CodePeg.Value, Int]): Int = {
      left.foldLeft(0)((hits, pegCount) => hits + math.min(pegCount._2, right.getOrElse(pegCount._1, 0)))
    }

    val filteredPegs = diff(code)

    calculateHits(countColours(filteredPegs._1), countColours(filteredPegs._2))
  }

  def exactHits(code: Code): Int = {
    exactHitPegs(code).length
  }

  def length: Int = pegs.length

  private def exactHitPegs(code: Code): List[CodePeg.Value] = {
    pegs.zip(code.pegs).filter(pegTuple => pegTuple._1 == pegTuple._2).map(_._1)
  }

  override def toString: String = "Code(" + pegs.foldLeft("")((s, peg) => s + peg + ", ").dropRight(2).trim + ")"
}
