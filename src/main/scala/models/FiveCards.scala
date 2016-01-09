package models

import utils.CardOps

case class FiveCards(card1: Card, card2: Card, card3: Card, card4: Card, card5: Card) extends Ordered[FiveCards]
with CardOps {
  def cards: List[Card] = List(card1, card2, card3, card4, card5)

  override def compare(that: FiveCards): Int = {
    compareCardLists(sortCardsInDescending(cards), sortCardsInDescending(that.cards))
  }
}


