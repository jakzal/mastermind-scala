package mastermind

class DecodingBoard(val gameUuid: GameUuid, private val secretCode: Code, val numberOfAttempts: Int) {
  private var guesses = Seq.empty[Guess]

  def tryCode(code: Code): Guess = {
    guesses = guesses :+ new Guess(secretCode, code)
    guesses.last
  }

  def lastGuess(): Guess = guesses.last
}
