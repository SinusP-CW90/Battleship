package de.htwg.se.battleship.model

case class Player(name: String = "Player Name") {
  override def toString:String = name
  def isSet: Boolean = name != ""

  def setDefaultPlayerNames(): Vector[Player] =Vector(Player("Player 1"),Player("Player 2"))

  def playerNamesToString(names: Vector[Player]): String ="Hello " + names(0) +" and " + names(1)
}