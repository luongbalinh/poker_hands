package utils

import models.{Card, CardSuit, CardValue}

import scala.annotation.tailrec

trait CardOps {

  def cardsBySuit(cards: List[Card]): Map[CardSuit, List[Card]] = cards.groupBy(_.suit)

  def cardsByValues(cards: List[Card]): Map[CardValue, List[Card]] = cards.groupBy(_.value)

  def sortCardsInDescending(cards: List[Card]): List[Card] = cards.sorted.reverse

  def highestCard(cards: List[Card]): Card = cards.sorted.last

  def cardsAreOfSameSuit(cards: List[Card]): Boolean = cardsBySuit(cards).size == 1

  def cardsHaveConsecutiveValues(cards: List[Card]): Boolean =
    cards.sorted.sliding(2).forall(pair => pair(1).compare(pair.head) == 1)

  @tailrec
  final def compareCardLists(cardList1: List[Card], cardList2: List[Card]): Int = cardList1 match {
    case Nil => 0
    case head :: tail =>
      if (head.compare(cardList2.head) == 0) {
        compareCardLists(tail, cardList2.tail)
      }
      else {
        head.compare(cardList2.head)
      }
  }
}
