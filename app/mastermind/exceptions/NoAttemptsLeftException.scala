package mastermind.exceptions

case class NoAttemptsLeftException(numberOfAttempts: Int) extends Exception(s"All of $numberOfAttempts attempts were used.") {
}
