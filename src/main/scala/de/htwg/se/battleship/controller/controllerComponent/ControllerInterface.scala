package de.htwg.se.battleship.controller.controllerComponent

import de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates.GameState
import de.htwg.se.battleship.model.battlefieldComponent.CellInterface
import play.api.libs.json.JsValue

import scala.swing.Publisher

trait ControllerInterface extends Publisher{
  var gameState: GameState
  var shipsToSetP1:Int
  var shipsToSetP2:Int
  var playerSite: String
  var currentGameState: String

  def switchPlayer:Unit
  def battlefieldSize:Int
  def blockSize:Int
  def createEmptyBattlefield(size:Int):Unit
  def createRandomBattlefield(player:String, size:Int):Unit
  def start(input: String): Boolean
  def setPlayerNames():String
  def undo():Unit
  def redo():Unit
  def save(): Unit
  def gridToJson: JsValue
  def load(): Unit
  def resize(newSize:Int):Unit
  def cell(row:Int, col:Int):CellInterface
  def cellL(row:Int, col:Int):CellInterface
  //def set(player:String,row: Int, col: Int, value: Int):Unit
  def set(rowString: String, colString: String):Unit
  def setInGUI(playerSite:String, row: Int, col: Int): Unit
  def checkIsInRange(row: Int, col: Int): Boolean
  def setL(row:Int, col:Int, value:Int):Unit
  def createShip(shiptype: String):Unit
  def isSet(row:Int, col:Int):Boolean
  def isSetR(row:Int, col:Int):Boolean
  def playgroundToString:String
  //def gameStatus:GameState
  def test(): Unit
}


import scala.swing.event.Event
class CellChanged extends Event
case class BattlefieldSizeChanged(newSize: Int) extends Event

