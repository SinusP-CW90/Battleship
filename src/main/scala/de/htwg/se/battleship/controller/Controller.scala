package de.htwg.se.battleship.controller

import de.htwg.se.battleship.controller.GameStatus._
import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.Observable

class Controller(var pgP1L :Battlefield, var pgP2R: Battlefield) extends Observable {
  var gameStatus: GameStatus = IDLE
  def singleton(): Unit ={
    Singleton.singletonFunction
  }
  def setPlayerNames: String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R)

  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Battlefield(size)
    pgP2R = new Battlefield(size)
    notifyObservers
  }

  def start(): Unit ={
    gameStatus=GameStatus.START
    //pgP1L.playgroundString(pgP1L, pgP2R)
    //pgP1L.start(pgP1L,pgP2R)
    notifyObservers
  }
 def createShip(shiptype: String){
    val ship = Ship(shiptype)
    ship.swim
  }

  def set(player:String,row: Int, col: Int, value: Int):Unit = {
    if (player == "l") pgP1L = pgP1L.set(row, col, value)
    if (player == "r") pgP2R = pgP2R.set(row, col, value)
    if (pgP1L.isWinning(pgP2R)) gameStatus=GameStatus.WIN
    notifyObservers
  }

  def setL(row: Int, col: Int, value: Int):Unit = {
    pgP1L = pgP1L.set(row, col, value)
    pgP2R.isWinning(pgP2R);
    notifyObservers
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    if (player == "l") pgP1L = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    if (player == "r") pgP2R = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    notifyObservers
  }

}