package de.htwg.se.battleship.controller

import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.{Observable, UndoManager}
import scala.swing.Publisher

class Controller(var pgP1L :Battlefield, var pgP2R: Battlefield) extends Observable with Publisher {
  var gameState: GameState = GameState(this)
  private val undoManager = new UndoManager
  def gridSize:Int = pgP1L.size
  def blockSize:Int = Math.sqrt(pgP1L.size).toInt
  //mal schauen obs klappt
  def statusText:String = this.gameState.state.toString
  def setPlayerNames: String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R)

  //übernhame
  def cell(row:Int, col:Int) = pgP2R.cell(row,col)
  //def available(row:Int, col:Int):Set[Int] = pgP1L.available(row, col)

  //def isGiven(row: Int, col: Int):Boolean = pgP1L.cell(row, col).given
  def isSet(row:Int, col:Int):Boolean = pgP1L.cell(row, col).isSet
  //def available(row:Int, col:Int):Set[Int] = pgP1L.available(row, col)

  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Battlefield(size)
    pgP2R = new Battlefield(size)
    publish(new CellChanged)
    notifyObservers
  }

  def start(input: String): Boolean = {
    gameState.handle(input)
    notifyObservers
    true
  }
 def createShip(shiptype: String){
    val ship = Ship(shiptype)
    ship.swim
  }

  def set(player:String,row: Int, col: Int, value: Int):Unit = {
    undoManager.doStep(new PlayerSetCommand(player, row, col, value, this))
    gameState.handle("play")
    publish(new CellChanged)
    notifyObservers
  }

  def setL(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameState.handle("play")
    pgP2R.isWinning(pgP2R);
    publish(new CellChanged)
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    gameState.handle("UNDO")
    publish(new CellChanged)
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    gameState.handle("REDO")
    publish(new CellChanged)
    notifyObservers
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    gameState.handle("random")
    if (player == "l") pgP1L = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    if (player == "r") pgP2R = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    publish(new CellChanged)
    notifyObservers
  }

}