import models._

object Cards {
  val suits = List(Heart, Diamond, Spade, Club)
  val values = List(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val pack = for {
    s <- suits
    v <- values
  } yield Card(v, s)
}
