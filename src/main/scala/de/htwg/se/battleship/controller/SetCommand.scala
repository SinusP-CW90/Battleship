package de.htwg.se.battleship.controller

import de.htwg.se.battleship.util.Command

import scala.util.Try

class SetCommand(row:Int, col: Int, value:Int, controller: Controller) extends Command {
  override def doStep(): Try[_] = Try ( controller.pgP1L = controller.pgP1L.set(row, col, value))

  override def undoStep(): Try[_] = Try (controller.pgP1L = controller.pgP1L.set(row, col, 0))

  override def redoStep(): Try[_] = Try (controller.pgP1L = controller.pgP1L.set(row, col, value))
}