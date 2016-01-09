package rules

import models.{FiveCards, Hand, CardValue}
import utils.CardOps

case class StraightFlush(highestCardValue: CardValue) extends Hand {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => highestCardValue.compare(straightFlush.highestCardValue)
    case _ => 1
  }
}

object StraightFlush extends CardOps {
  def unapply(fiveCards: FiveCards): Option[CardValue] = {
    val cards = fiveCards.cards
    if (cardsAreOfSameSuit(cards) && cardsHaveConsecutiveValues(cards)) {
      Some(highestCard(cards).value)
    }
    else {
      None
    }
  }
}
