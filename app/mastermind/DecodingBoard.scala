package mastermind

import mastermind.exceptions.NoAttemptsLeftException

class DecodingBoard(val gameUuid: GameUuid, private val secretCode: Code, val numberOfAttempts: Int) {
  private var guesses = Vector.empty[Guess]

  def tryCode(code: Code): Guess = {
    if (isGameFinished()) throw new NoAttemptsLeftException(numberOfAttempts)

    guesses = guesses :+ new Guess(secretCode, code)
    guesses.last
  }

  private def isGameFinished(): Boolean = isGameWon || isGameLost

  def isGameLost(): Boolean = guesses.length == numberOfAttempts && !isGameWon

  def isGameWon(): Boolean = if (guesses.nonEmpty) guesses.last.isCodeBroken else false

  def lastGuess(): Guess = guesses.last
}
