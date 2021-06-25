package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.CellInterface

/**The class Cell represents a Cell of the Battlefield.
 * @param value - The Int value can be set to 0 for empty, 1 for Ship ist set and 2 for ship on this place ist hit
 */
//TODO exclude all other Int and Strings
case class Cell(value: Int) extends CellInterface{
  def isSet: Boolean = value != 0

  override def toString: String = value match {
    case 0 => "."
    case 1 => "X"
    case 2 => "o"
    case _ => "?"
  }

}

/**Cell Object to write and read the cells into Json */
object Cell {
  import play.api.libs.json._
  implicit val cellWrites = Json.writes[Cell]
  implicit val cellReads = Json.reads[Cell]
}
