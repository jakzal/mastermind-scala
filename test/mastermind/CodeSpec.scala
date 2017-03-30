package mastermind

import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CodeSpec extends FlatSpec with Matchers {
  "Code" should "be created from a list of colour strings" in {
    val code = Code("Red Green Blue Yellow".split(" ").toList)

    code.pegs should be (List(CodePeg.Red, CodePeg.Green, CodePeg.Blue, CodePeg.Yellow))
  }

  "Code" should "be created from a string of colours" in {
    val code = Code("Red Green Blue Yellow")

    code.pegs should be (List(CodePeg.Red, CodePeg.Green, CodePeg.Blue, CodePeg.Yellow))
  }
}
