package de.htwg.se.battleship.model

trait Ship {
  def swim = println("ship is swimming")
}
private class defaultShip extends Ship {

}
private class miniShip extends Ship {
  override def swim: Unit = println("Mini-Ship is swimming")
}
private class longShip extends Ship {
  override def swim: Unit = println("Long-Ship is swimming")
}
object Ship {
  def apply(kind: String) = kind match {
    case "default" => new defaultShip()
    case "mini" => new miniShip()
    case "long" => new longShip()
  }
}

