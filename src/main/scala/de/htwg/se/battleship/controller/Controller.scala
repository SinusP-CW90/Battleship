package de.htwg.se.battleship.controller

import de.htwg.se.battleship.controller.GameStatus._
import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.Observable

class Controller(var pgP1L :Battlefield, var pgP2R: Battlefield) extends Observable {
  var gameStatus: GameStatus = IDLE
  def setPlayerNames: String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R)

  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Battlefield(size)
    pgP2R = new Battlefield(size)
    notifyObservers
  }

  def start(): Unit ={
    //pgP1L.playgroundString(pgP1L, pgP2R)
    //pgP1L.start(pgP1L,pgP2R)
    notifyObservers
  }
  /*
  def set(player:String,row: Int, col: Int, value: Int):Unit = {
    if (player == "l") pgP1L = pgP1L.set(row, col, value)
    if (player == "r") pgP2R = pgP2R.set(row, col, value)
    notifyObservers
  }
  */
  def setL(row: Int, col: Int, value: Int):Unit = {
    pgP1L = pgP1L.set(row, col, value)
    notifyObservers
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    if (player == "l") pgP1L = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    if (player == "r") pgP2R = (new BattlefieldCreateRandomStrategy).createNewGrid(size)
    notifyObservers
  }

/*
//TODO hier überarbeiten
  def set(pg: Battlefield, row: Int, col: Int, value: Int):Unit = {
    var pgNew = pg.set(row, col, value)
    notifyObservers
  }

   */
}