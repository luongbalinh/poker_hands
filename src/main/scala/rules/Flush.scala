package rules

import models.{FiveCards, Hand}
import utils.CardOps

case class Flush(fiveCards: FiveCards) extends Hand {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => -1
    case fullHouse: FullHouse => -1
    case flush: Flush => fiveCards.compare(flush.fiveCards)
    case _ => 1
  }
}

object Flush extends CardOps {
  def unapply(fiveCards: FiveCards): Option[FiveCards] = {
    if (cardsAreOfSameSuit(fiveCards.cards)) {
      Some(fiveCards)
    }
    else {
      None
    }
  }
}
