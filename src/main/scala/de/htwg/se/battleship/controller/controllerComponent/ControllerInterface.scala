package de.htwg.se.battleship.controller.controllerComponent

import de.htwg.se.battleship.model.battlefieldComponent.CellInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def battlefieldSize:Int
  def blockSize:Int
  def createEmptyBattlefield(size:Int):Unit
  def createRandomBattlefield(player:String, size:Int):Unit
  def start(input: String): Boolean

  def undo():Unit
  def redo():Unit
  def resize(newSize:Int):Unit
  def cell(row:Int, col:Int):CellInterface
  def set(player:String,row: Int, col: Int, value: Int):Unit
  def setL(row:Int, col:Int, value:Int):Unit
  def isSet(row:Int, col:Int):Boolean
  def playgroundToString:String
  //def gameStatus:GameState
  def statusText:String
}

import scala.swing.event.Event

class CellChanged extends Event
case class BattlefieldSizeChanged(newSize: Int) extends Event

