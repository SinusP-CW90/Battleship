package de.htwg.se.battleship.controller

import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.Observable

class Controller(var pgP1L :Battlefield, var pgP2R: Battlefield) extends Observable {
  def setPlayer(): Unit ={
    val player1 = Player("p1")
    val player2 = Player("p2")
    println("Hello " +player1 +" and " +player2)
  }


  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Battlefield(size)
    pgP2R = new Battlefield(size)
    notifyObservers
  }
  def start(): Unit ={
    pgP1L.playgroundString(pgP1L, pgP2R)
    //pgP1L.start(pgP1L,pgP2R)
    notifyObservers
  }

  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R)

//TODO hier überarbeiten
  def set(pg: Battlefield, row: Int, col: Int, value: Int):Unit = {
    var pgNew = pg.set(row, col, value)
    notifyObservers
  }
}