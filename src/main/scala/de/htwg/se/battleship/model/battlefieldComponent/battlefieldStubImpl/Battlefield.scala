package de.htwg.se.battleship.model.battlefieldComponent.battlefieldStubImpl

import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Cell
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}

class Battlefield(var size:Int) extends BattlefieldInterface{
  size=1
  override def cell(row: Int, col: Int): CellInterface = EmptyCell
  override def set(row: Int, col: Int, value: Int): BattlefieldInterface = this
  override def setRowWithLetter(rowString: String, columnString: String, value: Int): BattlefieldInterface = this
  //override def shoot(pg: BattlefieldInterface, row: Int, col: Int): BattlefieldInterface = this
  //override def isWinning(pg: BattlefieldInterface): Boolean = false //???
  override def battlefieldString(playgroundLeft: BattlefieldInterface, playgroundRight: BattlefieldInterface): String = "battlefieldString"
}

object EmptyCell extends CellInterface {
  def value: Int = 0
  def isSet: Boolean = false
}
