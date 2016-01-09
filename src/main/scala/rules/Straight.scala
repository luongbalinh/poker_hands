package rules

import models.{FiveCards, Hand, CardValue}
import utils.CardOps

case class Straight(highestCardValue: CardValue) extends Hand {
  override def compare(that: Hand): Int = that match {
    case straight: Straight => highestCardValue.compare(straight.highestCardValue)
    case _ => 1
  }
}

object Straight extends CardOps {
  def unapply(fiveCards: FiveCards): Option[CardValue] = {
    val cards = fiveCards.cards
    if (cardsHaveConsecutiveValues(cards)) {
      Some(highestCard(cards).value)
    }
    else {
      None
    }
  }
}
