package de.htwg.se.battleship.controller.controllerComponent

import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}

import scala.swing.Publisher
import scala.util.Try

trait ControllerInterface extends Publisher{

  def gridSize:Int
  def blockSize:Int
  def createEmptyPlayground(size:Int):Unit
  def createRandomBattlefield(player:String, size:Int):Unit
  def undo:Unit
  def redo:Unit
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
case class GridSizeChanged(newSize: Int) extends Event
class CandidatesChanged extends Event

