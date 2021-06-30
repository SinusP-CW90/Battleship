package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.CellChanged
import de.htwg.se.battleship.controller.controllerComponent.{BattlefieldSizeChanged, ControllerInterface}
import de.htwg.se.battleship.util._

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def processInputLine(input: String):Unit = {

    input match {
      case "q" =>
      case "set" => println(controller.setPlayerNames());
      case "mini"|"2x2"|"set size mini" => controller.createEmptyBattlefield(2);
      case "s"|"small"|"3x3"|"set size small" => controller.createEmptyBattlefield(3);
      case "tiny"|"4x4"|"set size tiny" => controller.createEmptyBattlefield(4);
      case "m"|"medium"|"6x6"|"set size medium" => controller.createEmptyBattlefield(6);
      case "l"|"large"|"9x9"|"set size large" => controller.createEmptyBattlefield(9);
      case "start" => controller.start("start")
      case "rl" => controller.createRandomBattlefield("l", controller.battlefieldSize)
      case "rr" => controller.createRandomBattlefield("r", controller.battlefieldSize)
      case "rrr" => controller.createRandomBattlefield("r", controller.battlefieldSize)
                    controller.createRandomBattlefield("l", controller.battlefieldSize)
                    controller.gameState.handle("shoot")
      case "msw" => controller.createShip("mini");
      case "lsw" => controller.createShip("long");
      case "sw" => controller.createShip("default");
      case "setL" => controller.setL(0,0,0)//for the Gui
      case "undo" => controller.undo();
      case "redo" => controller.redo();
      case "save" => controller.save();
      case "load" => controller.load();
      case "sp"=> controller.switchPlayer;
      case _ =>  input.toList.filter(c => c != ' ').map(c => c.toString) match {
          case row :: column :: Nil => controller.set(row,column);
          case _ =>println("please set a valid String");
      }
    }
  }

  reactions += {
    case event: BattlefieldSizeChanged => printTui()
    case event: CellChanged     => printTui()
  }

  def printTui(): Unit = {
    println(controller.playgroundToString)
    //println(GameState.message(controller.gameStatus))
  }

}
