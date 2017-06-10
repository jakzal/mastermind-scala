package mastermind

import mastermind.exceptions.InvalidCodeLengthException

import scala.util.Random

object RandomCodeMaker {
  def apply(length: Int): CodeMaker = {
    if (length < 1) {
      throw new InvalidCodeLengthException(length)
    }
    () => Code(List.fill(length)(CodePeg(Random.nextInt(CodePeg.maxId))))
  }
}
