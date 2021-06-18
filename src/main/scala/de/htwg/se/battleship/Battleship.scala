package de.htwg.se.battleship

import com.google.inject.Guice
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.aview.gui.SwingGui
import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.CellChanged
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield

import scala.io.StdIn.readLine

object Battleship {
  val battlefieldSize = 3
  /*
  val injector = Guice.createInjector(new BattleshipModule)
  val controller = injector.getInstance(classOf[ControllerInterface])

   */

  val controller = new Controller(new Battlefield(battlefieldSize),new Battlefield(battlefieldSize))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.publish(new CellChanged)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length>0) input=args(0)
    println(args.length)
    if (input.nonEmpty) tui.processInputLine(input)
    else
      do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
