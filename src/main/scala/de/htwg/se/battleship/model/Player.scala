package de.htwg.se.battleship.model

case class Player(name: String = "The Player") {
  override def toString:String = name
  def isSet: Boolean = name != ""
}