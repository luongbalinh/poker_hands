package rules

import models.{Card, FiveCards, Hand, CardValue}
import utils.CardOps

case class FullHouse(valueOfThreeCards: CardValue) extends Hand {
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => -1
    case fullHouse: FullHouse => valueOfThreeCards.compare(fullHouse.valueOfThreeCards)
    case _ => 1
  }
}

object FullHouse extends CardOps {
  def unapply(fiveCards: FiveCards): Option[CardValue] = {
    val map: Map[CardValue, List[Card]] = cardsByValues(fiveCards.cards)
    if (map.size == 2) {
      if (map.head._2.size == 3) {
        Some(map.head._1)
      }
      else if(map.last._2.size == 3) {
        Some(map.last._1)
      }
      else {
        None
      }
    }
    else {
      None
    }
  }
}
