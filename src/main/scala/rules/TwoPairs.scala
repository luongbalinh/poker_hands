package rules

import models.{Card, CardValue, FiveCards, Hand}
import utils.CardOps

case class TwoPairs(
  firstTwoGroupValue: Card, secondTwoGroupValue: Card, remainingCard: Card) extends Hand with CardOps {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => -1
    case fullHouse: FullHouse => -1
    case flush: Flush => -1
    case straight: Straight => -1
    case threeOfAKind: ThreeOfAKind => -1
    case twoPairs: TwoPairs => compareCardLists(getSeparateCards, twoPairs.getSeparateCards)
    case _ => 1
  }

  private def getSeparateCards: List[Card] = List(firstTwoGroupValue, secondTwoGroupValue, remainingCard)
}

object TwoPairs extends CardOps {
  def unapply(fiveCards: FiveCards): Option[(Card, Card, Card)] = {
    val map: Map[CardValue, List[Card]] = cardsByValues(fiveCards.cards)
    if (map.values.map{_.size} == List(1, 2, 2)) {
      val twoGroupCards = map.values.toList.filter(_.size == 2).map(_.head).sorted.reverse
      val remainingCard = map.values.find(_.size == 1).get.head
      Some(twoGroupCards.head, twoGroupCards(1), remainingCard)
    }
    else {
      None
    }
  }
}