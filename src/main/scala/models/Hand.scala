package models

import rules._

trait Hand extends Ordered[Hand] {}

object Hand {
  def apply(fiveCards: FiveCards): Hand = fiveCards match {
    case StraightFlush(v) => StraightFlush(v)
    case FourOfAKind(v) => FourOfAKind(v)
    case FullHouse(v) => FullHouse(v)
    case Flush(cards) => Flush(cards)
    case Straight(v) => Straight(v)
    case ThreeOfAKind(v) => ThreeOfAKind(v)
    case TwoPairs(firstTwoGroupCard, secondTwoGroupCard, remainingCard) => TwoPairs(firstTwoGroupCard, secondTwoGroupCard, remainingCard)
    case Pair(twoGroupCard, cards) => Pair(twoGroupCard, cards)
    case HighCard(cards) => HighCard(cards)
  }
}
