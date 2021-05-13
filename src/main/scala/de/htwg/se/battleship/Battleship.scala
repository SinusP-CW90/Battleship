package de.htwg.se.battleship


import de.htwg.se.battleship.model._
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.controller.Controller

import scala.io.StdIn.readLine

object Battleship {
  val controller = new Controller(new Battlefield(2),new Battlefield(2))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length>0) input=args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    else
      do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
