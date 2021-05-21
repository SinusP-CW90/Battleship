package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller._
import de.htwg.se.battleship.util._

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String):Unit = {

    input match {
      case "q" =>
      case "set" => println(controller.setPlayerNames);
      case "s"|"small"|"3x3"|"set size small" => controller.createEmptyPlayground(3);
      case "m"|"medium"|"6x6"|"set size medium" => controller.createEmptyPlayground(6);
      case "l"|"large"|"9x9"|"set size large" => controller.createEmptyPlayground(9);
      case "start" => controller.start()
      case "rl" => controller.createRandomBattlefield("l", controller.pgP1L.size)
      case "rr" => controller.createRandomBattlefield("r", controller.pgP2R.size)
      case "msw" => controller.createShip("mini");
      case "lsw" => controller.createShip("long");
      case "sw" => controller.createShip("default");
      case "undo" => controller.undo;
      case "redo" => controller.redo;
      case _ => input.toList.filter(c => c != ' ').filter(_.isDigit).map(c => c.toString.toInt) match {
        case row :: column :: value :: Nil => controller.setL(row, column, value);
        case _ =>
          input match {
            case "a1" => controller.set("l", 0, 0, 1)
            case "a2" => controller.set("l", 0, 1, 1)
            case "b1" => controller.set("l", 1, 0, 1)
            case "b2" => controller.set("l", 1, 1, 1)
            case "A1" => controller.set("r", 0, 0, 1)
            case "A2" => controller.set("r", 0, 1, 1)
            case "B1" => controller.set("r", 1, 0, 1)
            case "B2" => controller.set("r", 1, 1, 1)
            case "sA1" => controller.set("r", 0, 0, 2)
            case "sA2" => controller.set("r", 0, 1, 2)
            case "sB1" => controller.set("r", 1, 0, 2)
            case "sB2" => controller.set("r", 1, 1, 2)
            case _ => println("please set a valid String");
          }
      }
    }
  }

  override def update: Boolean = {
    println(controller.playgroundToString)
    println(GameStatus.message(controller.gameStatus))
    controller.gameStatus=GameStatus.IDLE
    true
  }
}
