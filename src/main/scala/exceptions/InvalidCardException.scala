package exceptions

case class InvalidCardException(message: String, error: Throwable) extends RuntimeException(message, error)

class InvalidCardSuitException(suit: Char, error: Throwable)
  extends InvalidCardException(s"Invalid suit $suit", error)

class InvalidCardValueException(value: Char, error: Throwable)
  extends InvalidCardException(s"Invalid suit $value", error)
