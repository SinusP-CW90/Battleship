package de.htwg.se.battleship.model.battlefieldComponent

trait BattlefieldInterface {
  def size: Int
  def cell(row: Int, col: Int): CellInterface
  def set(row:Int, col:Int, value:Int): BattlefieldInterface
  def setRowWithLetter(rowString: String, columnString: String, value: Int): BattlefieldInterface
  def shoot(pg: BattlefieldInterface, row: Int, col: Int): BattlefieldInterface
  def isWinning(pg: BattlefieldInterface): Boolean
  def battlefieldString(playgroundLeft: BattlefieldInterface, playgroundRight: BattlefieldInterface): String
}

trait CellInterface {
  def value:Int
  def isSet: Boolean
}
