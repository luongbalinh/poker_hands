package models

sealed trait CardSuit

case object Spade extends CardSuit {
  override def toString: String = "♠"
}

case object Club extends CardSuit {
  override def toString: String = "♣"
}

case object Diamond extends CardSuit {
  override def toString: String = "♦"
}

case object Heart extends CardSuit {
  override def toString: String = "♥"
}

