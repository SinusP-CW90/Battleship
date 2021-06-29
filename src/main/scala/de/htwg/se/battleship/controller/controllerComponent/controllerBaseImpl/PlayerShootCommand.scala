package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.battleship.util.Command

import scala.util.Try

class PlayerShootCommand(player: String, row:Int, col: Int, controller: Controller) extends Command {
  override def doStep(): Try[_] = Try {
    if (player == "l") controller.pgP2R = controller.pgP2R.shoot(controller.pgP2R,row, col)
    if (player == "r") controller.pgP1L = controller.pgP1L.shoot(controller.pgP1L,row, col)
  }

  //todo auf den richtign wert
  override def undoStep(): Try[_] = Try {
    if (player == "l") controller.pgP2R = controller.pgP1L.set(row, col, 0)
    if (player == "r")  controller.pgP1L = controller.pgP2R.set(row, col, 0)
  }

  override def redoStep(): Try[_] = Try {
    if (player == "l") controller.pgP2R = controller.pgP2R.shoot(controller.pgP2R,row, col)
    if (player == "r")  controller.pgP1L = controller.pgP1L.shoot(controller.pgP1L,row, col)
  }
}