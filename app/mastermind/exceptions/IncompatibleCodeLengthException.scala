package mastermind.exceptions

case class IncompatibleCodeLengthException(expectedLength: Int, actualLength: Int) extends Exception(s"The secret is $expectedLength colours long, but the guess code is $actualLength colours long..") {
}
