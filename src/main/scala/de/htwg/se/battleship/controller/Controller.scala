package de.htwg.se.battleship.controller

import de.htwg.se.battleship.model._
import de.htwg.se.battleship.util.Observable

class Controller(var pgP1L :Playground, var pgP2R: Playground) extends Observable {
  def createEmptyPlayground(size: Int):Unit = {
    pgP1L = new Playground(size)
    pgP2R = new Playground(size)
    notifyObservers
  }
  def start(): Unit ={
    pgP1L.start(pgP1L,pgP2R)
    notifyObservers
  }

  def playgroundToString: String = pgP1L.playgroundString(pgP1L, pgP2R,"p1")

//TODO hier überarbeiten
  def set(pg: Playground, row: Int, col: Int, value: Int):Unit = {
    var pgNew = pg.set(row, col, value)
    notifyObservers
  }

}
