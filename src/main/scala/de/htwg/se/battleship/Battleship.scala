package de.htwg.se.battleship

import com.google.inject.{Guice, Injector}
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.aview.gui.SwingGui
import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.CellChanged

import scala.io.StdIn.readLine

object Battleship {

  val injector: Injector = Guice.createInjector(new BattleshipModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  //TODO activate GUI - it is deactivated cause fail/Error in travis CI Build
  //val gui = new SwingGui(controller)
  controller.publish(new CellChanged)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length>0) input=args(0)
    if (input.nonEmpty) tui.processInputLine(input)
    else
      do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
