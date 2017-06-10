package mastermind.exceptions

case class InvalidCodeLengthException(message: String) extends Exception(message) {
  def this(length: Int, maxLength: Int) = this(s"Code length should be greater than 0 and less than $maxLength, but got $length.")
  def this(length: Int) = this(s"Code length should be greater than zero, but got $length.")
}
