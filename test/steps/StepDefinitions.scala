package steps

import cucumber.api.PendingException
import cucumber.api.scala.{EN, ScalaDsl}

class StepDefinitions extends ScalaDsl with EN {
  Given("""^a decoding board of (\d+) attempts$""") { (arg0: Int) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Given("""^the code maker placed the "([^"]*)" pattern on the board$""") { (arg0: String) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  When("""^I try to break the code with "([^"]*)"$""") { (arg0: String) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Then("""^the code maker should give me "([^"]*)" feedback on my guess$""") { (arg0: String) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  When("""^I try to break the code with an invalid pattern (\d+) times$""") { (arg0: Int) =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  When("""^I break the code in the final guess$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Then("""^I should win the game$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }

  Then("""^I should loose the game$""") { () =>
    //// Write code here that turns the phrase above into concrete actions
    throw new PendingException()
  }
}
