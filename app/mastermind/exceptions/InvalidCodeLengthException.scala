package mastermind.exceptions

case class InvalidCodeLengthException(length: Int) extends Exception(s"Code length should be greater than zero, but got $length.") {
}
