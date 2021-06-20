package de.htwg.se.battleship.controller.controllerComponent.controllerStubImpl

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}

class Controller(var battlefield: BattlefieldInterface) extends ControllerInterface{

  battlefield = new Battlefield(1)

  override def battlefieldSize: Int = 1

  override def blockSize: Int = 1

  override def createEmptyBattlefield(size:Int):Unit = {}
  override def createRandomBattlefield(player:String, size:Int):Unit = {}

  override def undo(): Unit = {}

  override def redo(): Unit = {}

  override def resize(newSize: Int): Unit = {}

  override def cell(row: Int, col: Int): CellInterface = battlefield.cell(row, col)

  override def set(player:String,row: Int, col: Int, value: Int): Unit = {}

  override def isSet(row: Int, col: Int): Boolean = false

  override def playgroundToString: String = battlefield.toString

/*
  override def gameState: GameState = "IDLE"
  override def statusText: String = GameState.message(gameStatus)
 */

  override def setL(row: Int, col: Int, value: Int): Unit = {}

  //override def gameStatus: GameState = ???

  override def statusText: String = "state Text"

  override def start(input: String): Boolean = true
}
