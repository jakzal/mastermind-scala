package mastermind

import mastermind.exceptions.NoAttemptsLeftException

case class DecodingBoard(
                          val gameUuid: GameUuid,
                          val secretCode: Code,
                          val numberOfAttempts: Int,
                          private var guessAttempts: Vector[Guess] = Vector.empty[Guess]
                        ) {

  def tryCode(code: Code): Guess = {
    if (isGameFinished()) throw new NoAttemptsLeftException(numberOfAttempts)

    guessAttempts = guessAttempts :+ new Guess(secretCode, code)
    guessAttempts.last
  }

  private def isGameFinished(): Boolean = isGameWon || isGameLost

  def isGameLost(): Boolean = guesses.length == numberOfAttempts && !isGameWon

  def isGameWon(): Boolean = if (guesses.nonEmpty) guesses.last.isCodeBroken else false

  def lastGuess(): Guess = guesses.last

  def guesses(): Vector[Guess] = guessAttempts
}
