package mastermind

class Guess(val secretCode: Code, val guessCode: Code) {
  def colourHits(): Int = secretCode.colourHits(guessCode)

  def exactHits(): Int = secretCode.exactHits(guessCode)

  def isCodeBroken(): Boolean = secretCode == guessCode
}
