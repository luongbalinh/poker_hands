import models._
import org.scalacheck._
import org.scalatest._
import org.scalatest.prop.PropertyChecks
import rules.BasicRule.CardsInHand
import rules.{FourOfAKind, StraightFlush}

class PokerSpec extends FlatSpec with Matchers with PropertyChecks {

  val cards = Gen.oneOf(Cards.pack)
  val values = Gen.oneOf(Cards.values)
  val twos = Gen.oneOf(Cards.suits).map(Card(Two, _))
  val straightFlushes = Gen.oneOf(Cards.values).map(StraightFlush(_))
  val fourOfAKinds = Gen.oneOf(Cards.values).map(FourOfAKind(_))
  val fiveCards = Gen.pick(CardsInHand, Cards.pack)
  val hands = fiveCards.map(cards => Hand(FiveCards(cards.head, cards(1), cards(2), cards(3), cards(4))))
  val threeHands = for {
    h1 <- hands
    h2 <- hands
    h3 <- hands
  } yield (h1, h2, h3)

  "Card values" should "be ranked correctly" in {

  }

  "Twos" should "be lower than everything that is not a two and equal to other twos" in {
    forAll(twos, cards) {
      (two: Card, other: Card) => other match {
        case Card(Two, _) => other.compare(two) should equal(0)
        case card: Card => card should be > two
      }
    }
  }
  "Straight Flush" should "be higher than any other hand that is not a straight flush" in {
    forAll(hands, straightFlushes) {
      (other: Hand, sf: StraightFlush) => other match {
        case otherSf: StraightFlush => sf.compare(otherSf) shouldBe
          sf.highestCardValue.compare(otherSf.highestCardValue)
        case h: Hand => sf should be > h
      }
    }
  }

  "Straight flushes" should "be ranked according to the highest card" in {
    forAll(straightFlushes, straightFlushes) {
      (sf1: StraightFlush, sf2: StraightFlush) => {
        sf1.highestCardValue.compare(sf2.highestCardValue) should equal(sf1.compare(sf2))
      }
    }
  }

  "FourOfAKind" should "be higher than any hand that is not a StraightFlush" in {
    forAll(hands, fourOfAKinds) {
      (other: Hand, foc: FourOfAKind) => other match {
        case h: StraightFlush => foc < h shouldBe true
        //get a bizarre diverging implicit expansion error here if we write it another way
        case h: FourOfAKind => foc.compare(h) shouldBe foc.fourGroupValue.compare(h.fourGroupValue)
        case h: Hand => foc should be > h
      }
    }
  }

  "FourOfAKinds" should "be ranked according to the highest card" in {
    forAll(fourOfAKinds, fourOfAKinds) {
      (foc1: FourOfAKind, foc2: FourOfAKind) => {
        foc1.fourGroupValue.compare(foc2.fourGroupValue) should equal(foc1.compare(foc2))
      }
    }
  }

  "Hand ordering" should "be so that if a < b and b < c, then a < c" in {
    forAll(threeHands) {
      th => {
        if ((th._1 < th._2) && (th._2 < th._3)) {
          th._1 should be < th._3
        }
      }
    }
  }
}
