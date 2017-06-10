package mastermind

import mastermind.exceptions.IncompatibleCodeLengthException

case class Guess(val secretCode: Code, val guessCode: Code) {
  if (guessCode.length != secretCode.length) {
    throw new IncompatibleCodeLengthException(guessCode.length, secretCode.length)
  }

  def colourHits(): Int = secretCode.colourHits(guessCode)

  def exactHits(): Int = secretCode.exactHits(guessCode)

  def isCodeBroken(): Boolean = secretCode == guessCode
}
