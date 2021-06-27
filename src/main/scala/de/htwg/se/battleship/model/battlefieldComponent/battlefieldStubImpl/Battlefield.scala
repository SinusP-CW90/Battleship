package de.htwg.se.battleship.model.battlefieldComponent.battlefieldStubImpl

import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}

class Battlefield(var size:Int) extends BattlefieldInterface{
  size=1
  def cell(row: Int, col: Int): CellInterface = EmptyCell
  def set(row: Int, col: Int, value: Int): BattlefieldInterface = this
  def createNewGrid(size: Int): BattlefieldInterface = this
  def valid: Boolean = true
  def reset(row: Int, col: Int): BattlefieldInterface = this
  def indexToRowCol(index: Int): (Int, Int) = (0,0)

  override def setRowWithLetter(rowString: String, columnString: String, value: Int): BattlefieldInterface = this
  //override def shoot(pg: BattlefieldInterface, row: Int, col: Int): BattlefieldInterface = this
  //override def isWinning(pg: BattlefieldInterface): Boolean = false //???
  override def battlefieldString(playgroundLeft: BattlefieldInterface, playgroundRight: BattlefieldInterface): String = ???
}

object EmptyCell extends CellInterface {
  def value: Int = 0
  def isSet: Boolean = false
}
