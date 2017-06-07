package mastermind

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class CodeSpec extends WordSpec with Matchers {
  "Code" should {
    "be created from a list of colour strings" in {
      val code = Code("Red", "Green", "Blue", "Yellow")

      code.pegs should be(List(CodePeg.Red, CodePeg.Green, CodePeg.Blue, CodePeg.Yellow))
    }

    "be created from a string of colours" in {
      val code = Code("Red Green Blue Yellow")

      code.pegs should be(List(CodePeg.Red, CodePeg.Green, CodePeg.Blue, CodePeg.Yellow))
    }
  }

  "exactHits" should {
    val exactHitsExamples = Table(
      ("codeString", "anotherCodeString", "exactHits"),
      ("Red Green Blue Yellow", "Purple Purple Purple Purple", 0),
      ("Red Green Blue Yellow", "Purple Red Purple Purple", 0),
      ("Red Green Blue Yellow", "Red Purple Purple Purple", 1),
      ("Red Green Blue Yellow", "Red Red Purple Purple", 1),
      ("Red Red Blue Yellow", "Red Purple Purple Purple", 1),
      ("Red Red Blue Yellow", "Red Red Blue Yellow", 4)
    )

    "return a number of exact hits" in {
      forAll(exactHitsExamples) { (codeString: String, anotherCodeString: String, exactHits: Int) =>
        val code = Code(codeString)
        val anotherCode = Code(anotherCodeString)

        code.exactHits(anotherCode) should be(exactHits)
      }
    }
  }

  "colourHits" should {
    val colourHitsExamples = Table(
      ("codeString", "anotherCodeString", "colourHits"),
      ("Red Green Blue Yellow", "Purple Purple Purple Purple", 0),
      ("Red Green Blue Yellow", "Purple Red Purple Purple", 1),
      ("Red Green Blue Yellow", "Red Purple Purple Purple", 0),
      ("Red Green Blue Yellow", "Red Red Purple Purple", 0),
      ("Red Green Blue Yellow", "Purple Purple Red Red", 1),
      ("Red Red Blue Yellow", "Purple Purple Red Purple", 1),
      ("Red Blue Blue Yellow", "Purple Purple Red Red", 1),
      ("Red Red Blue Yellow", "Red Purple Purple Purple", 0),
      ("Red Red Blue Yellow", "Red Red Blue Yellow", 0),
      ("Red Green Red Yellow", "Red Red Blue Yellow", 1),
      ("Red Green Blue Yellow", "Yellow Blue Green Red", 4)
    )

    "return a number of exact hits" in {
      forAll(colourHitsExamples) { (codeString: String, anotherCodeString: String, colourHits: Int) =>
        val code = Code(codeString)
        val anotherCode = Code(anotherCodeString)

        code.colourHits(anotherCode) should be(colourHits)
      }
    }
  }

  "toString" should {
    "return colour names" in {
      val code = Code("Red Green Blue")

      code.toString should be("Code(Red, Green, Blue)")
    }
  }

  "equals" should {
    "compare objects by value" in {
      val firstCode = Code("Red Green Blue")
      val secondCode = Code("Red Green Blue")
      val thirdCode = Code("Blue Green Red")

      firstCode.equals(secondCode) should be(true)
      firstCode.equals(thirdCode) should be(false)
    }
  }
}
