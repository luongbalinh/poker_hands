package models

import exceptions.{InvalidCardSuitException, InvalidCardValueException}

case class Card(value: CardValue, suit: CardSuit) extends Ordered[Card] {

  override def compare(that: Card): Int = value.compare(that.value)

  override def toString: String = s"${value.rank}$suit"
}

object Card {

  def apply(value: Char, suit: Char): Card = new Card(convertValue(value), convertSuit(suit))

  private def convertValue(value: Char): CardValue = value match {
    case twoToNine(v) => v
    case 'T' => Ten
    case 'J' => Jack
    case 'Q' => Queen
    case 'K' => King
    case 'A' => Ace
    case _ => throw new InvalidCardValueException(value, new RuntimeException())
  }

  private def convertSuit(suit: Char): CardSuit = suit match {
    case 'S' => Spade
    case 'C' => Club
    case 'D' => Diamond
    case 'H' => Heart
    case _ => throw new InvalidCardSuitException(suit, new RuntimeException())
  }

}

object twoToNine {
  def unapply(value: Char): Option[CardValue] = twoToNineMap.get(value)

  val twoToNineMap: Map[Char, CardValue] = Map(
    '2' -> Two,
    '3' -> Three,
    '4' -> Four,
    '5' -> Five,
    '6' -> Six,
    '7' -> Seven,
    '8' -> Eight,
    '9' -> Nine
  )
}
