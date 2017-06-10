package mastermind

import scala.util.Random

object RandomCodeMaker {
  def apply(length: Int): CodeMaker = () => Code(List.fill(length)(CodePeg(Random.nextInt(CodePeg.maxId))))
}
