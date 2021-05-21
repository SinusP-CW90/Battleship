package de.htwg.se.battleship.controller

import de.htwg.se.battleship.util.Command

class SetCommand(row:Int, col: Int, value:Int, controller: Controller) extends Command {
  override def doStep: Unit =   controller.pgP1L = controller.pgP1L.set(row, col, value)

  override def undoStep: Unit = controller.pgP1L = controller.pgP1L.set(row, col, 0)

  override def redoStep: Unit = controller.pgP1L = controller.pgP1L.set(row, col, value)
}