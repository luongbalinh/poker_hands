package rules

import models.{Card, FiveCards, Hand, CardValue}
import utils.CardOps

case class FourOfAKind(fourGroupValue: CardValue) extends Hand {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => fourGroupValue.compare(fourOfAKind.fourGroupValue)
    case _ => 1
  }
}

object FourOfAKind extends CardOps {
  def unapply(fiveCards: FiveCards): Option[CardValue] = {
    val fourCards: Map[CardValue, List[Card]] = cardsByValues(fiveCards.cards).filter(_._2.length == 4)
    if (fourCards.nonEmpty) Some(fourCards.values.head(0).value) else None
  }
}
