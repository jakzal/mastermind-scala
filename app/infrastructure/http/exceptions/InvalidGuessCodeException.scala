package infrastructure.http.exceptions

class InvalidGuessCodeException() extends Exception("Could not find a guess code in the request.") {
}
