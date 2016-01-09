package models

sealed trait CardValue extends Ordered[CardValue] {
  def rank: Int

  override def compare(that: CardValue): Int = rank - that.rank
}

object Two extends CardValue {
  override def rank: Int = 0
}

object Three extends CardValue {
  override def rank: Int = 1
}

object Four extends CardValue {
  override def rank: Int = 2
}

object Five extends CardValue {
  override def rank: Int = 3
}

object Six extends CardValue {
  override def rank: Int = 4
}

object Seven extends CardValue {
  override def rank: Int = 5
}

object Eight extends CardValue {
  override def rank: Int = 6
}

object Nine extends CardValue {
  override def rank: Int = 7
}

object Ten extends CardValue {
  override def rank: Int = 8
}

object Jack extends CardValue {
  override def rank: Int = 9
}

object Queen extends CardValue {
  override def rank: Int = 10
}

object King extends CardValue {
  override def rank: Int = 11
}

object Ace extends CardValue {
  override def rank: Int = 12
}
