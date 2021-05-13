package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller.Controller
import de.htwg.se.battleship.util._

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String):Unit = {

    input match {
      case "q" =>
      case "set" => controller.setPlayer;
      case "s"|"small"|"3x3"|"set size small" => controller.createEmptyPlayground(3);
      case "m"|"medium"|"6x6"|"set size medium" => controller.createEmptyPlayground(6);
      case "l"|"large"|"9x9"|"set size large" => controller.createEmptyPlayground(9);
      case "start" => controller.start()

      case _ => println("please set a valid String" )
    }
  }
  override def update: Unit =  println(controller.playgroundToString)
}
