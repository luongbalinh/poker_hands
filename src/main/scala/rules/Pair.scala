package rules

import models.{Card, CardValue, FiveCards, Hand}
import utils.CardOps

case class Pair(twoGroupCard: Card, cards: List[Card]) extends Hand with CardOps {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => -1
    case fullHouse: FullHouse => -1
    case flush: Flush => -1
    case straight: Straight => -1
    case threeOfAKind: ThreeOfAKind => -1
    case twoPairs: TwoPairs => -1
    case pair: Pair =>
      if (twoGroupCard.compare(pair.twoGroupCard) == 0) {
        compareCardLists(cards, pair.cards)
      }
      else {
        twoGroupCard.compare(pair.twoGroupCard)
      }
    case _ => 1
  }
}

object Pair extends CardOps {
  def unapply(fiveCards: FiveCards): Option[(Card, List[Card])] = {
    val map: Map[CardValue, List[Card]] = cardsByValues(fiveCards.cards)
    if (map.values.map(_.size) == List(1, 1, 1, 2)) {
      val twoGroupCard = map.values.toList.filter(_.size == 2).head.head
      val remainingCards = map.values.toList.filter(_.size == 1).map(_.head).sorted
      Some(twoGroupCard, remainingCards)
    }
    else {
      None
    }
  }
}
