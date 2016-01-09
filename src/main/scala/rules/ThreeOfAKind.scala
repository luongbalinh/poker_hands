package rules

import models.{Card, FiveCards, CardValue, Hand}
import utils.CardOps

case class ThreeOfAKind(threeGroupValue: CardValue) extends Hand{
  override def compare(that: Hand): Int = that match {
    case straightFlush: StraightFlush => -1
    case fourOfAKind: FourOfAKind => -1
    case fullHouse: FullHouse => -1
    case flush: Flush => -1
    case straight: Straight => -1
    case threeOfAKind: ThreeOfAKind => threeGroupValue.compare(threeOfAKind.threeGroupValue)
    case _ => 1
  }
}

object ThreeOfAKind extends CardOps {
  def unapply(fiveCards: FiveCards) : Option[CardValue] = {
    val map: Map[CardValue, List[Card]] = cardsByValues(fiveCards.cards)
    if(map.size == 3) {
      val threeCards =map.filter(x => x._2.size == 3)
      if(threeCards.nonEmpty) Some(threeCards.values.head.head.value) else None
    }
    else {
      None
    }
  }
}
