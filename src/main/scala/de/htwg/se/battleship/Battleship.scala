package de.htwg.se.battleship


import de.htwg.se.battleship.model._
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.controller.Controller

import scala.io.StdIn.readLine

object Battleship {
  val controller = new Controller(new Playground(3),new Playground(3))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {

    var input: String = ""
    do {
      println("Welcome to Battleship")
      println("To start the Game please enter 'start', or change the size of the Battlefield")
      println("after the start you can set your ships with `a1` or `b2` etc. ")
      input = readLine()
      tui.processInputLine(input)
    } while (input != "quit")
  }

}
