package de.htwg.se.battleship


import de.htwg.se.battleship.model._
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.controller.{CellChanged, Controller}
import de.htwg.se.battleship.aview.gui.SwingGui
import scala.io.StdIn.readLine

object Battleship {
  val battlefieldSize = 3;
  val controller = new Controller(new Battlefield(battlefieldSize),new Battlefield(battlefieldSize))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.publish(new CellChanged)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length>0) input=args(0)
    println(args.length)
    if (!input.isEmpty) tui.processInputLine(input)
    else
      do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
