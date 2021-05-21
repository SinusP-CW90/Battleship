package de.htwg.se.battleship.controller

import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.{Observable, UndoManager}

class Controller(var pgP1L :Battlefield, var pgP2R: Battlefield) extends Observable {
  var gameState: GameState = GameState(this)
  private val undoManager = new UndoManager

  def setPlayerNames: String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R)

  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Battlefield(size)
    pgP2R = new Battlefield(size)
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
    notifyObservers
  }

  def setL(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameState.handle("play")
    pgP2R.isWinning(pgP2R);
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    gameState.handle("random")
    if (player == "l") pgP1L = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    if (player == "r") pgP2R = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    notifyObservers
  }

}