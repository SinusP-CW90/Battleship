package de.htwg.se.battleship.util

import scala.util.Try

trait Command {
  def doStep():Try[_]
  def undoStep():Try[_]
  def redoStep():Try[_] //= doStep()

}
