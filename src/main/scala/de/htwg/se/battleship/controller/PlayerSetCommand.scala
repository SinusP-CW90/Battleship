package de.htwg.se.battleship.controller

import de.htwg.se.battleship.util.Command

class PlayerSetCommand(player: String, row:Int, col: Int, value:Int, controller: Controller) extends Command {
  override def doStep: Unit = {
    if (player == "l") controller.pgP1L = controller.pgP1L.set(row, col, value)
    if (player == "r")  controller.pgP2R = controller.pgP2R.set(row, col, value)
}

  override def undoStep: Unit = {
    if (player == "l") controller.pgP1L = controller.pgP1L.set(row, col, 0)
    if (player == "r")  controller.pgP2R = controller.pgP2R.set(row, col, 0)
  }

  override def redoStep: Unit = {
    if (player == "l") controller.pgP1L = controller.pgP1L.set(row, col, value)
    if (player == "r")  controller.pgP2R = controller.pgP2R.set(row, col, value)
  }
}
