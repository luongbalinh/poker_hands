package rules

import models.{FiveCards, Hand}
import utils.CardOps

case class HighCard(fiveCards: FiveCards) extends Hand {
  override def compare(that: Hand): Int = that match {
    case highCard: HighCard => fiveCards.compare(highCard.fiveCards)
    case _ => -1
  }
}

object HighCard extends CardOps {
  def unapply(fiveCards: FiveCards): Option[FiveCards] = Some(fiveCards)
}