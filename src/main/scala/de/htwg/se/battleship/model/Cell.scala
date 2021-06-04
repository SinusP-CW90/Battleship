package de.htwg.se.battleship.model

//This Class represents a Cell of the Battlefield.
//The passed Int value can be set to 0 for empty, 1 for Ship ist set and 2 for ship on this place ist hit
//TODO exclude all other Int and Strings
case class Cell(value: Int) {
  def isSet: Boolean = value != 0
  override def toString: String = value match {
    case 0 => "."
    case 1 => "X"
    case 2 => "o"
    case _ => "?"
  }


