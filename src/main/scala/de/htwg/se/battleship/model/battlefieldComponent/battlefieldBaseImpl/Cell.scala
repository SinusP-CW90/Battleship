package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.CellInterface

/**The class Cell represents a Cell of the Battlefield.
 * @param value The Int value can be set to 0 for empty, 1 for Ship ist set and 2 for ship on this place ist hit
 */
case class Cell(value: Int) extends CellInterface{
  def isSet: Boolean = value != 0

  override def toString: String = value match {
    case 0 => "." // is empty
    case 1 => "S" //ship is set
    case 2 => "X"//ship is hit
    case 3 => "o"//hit the water
    case _ => "?"//other/unknown values
  }

}

