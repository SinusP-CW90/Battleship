package de.htwg.se.battleship.util

import scala.util.Try

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil

  def doStep(command: Command):Try[_] = {
    undoStack = command::undoStack
    command.doStep()
  }
  def undoStep(): Try[_]  = {
    undoStack match {
      case  Nil => Try()
      case head::stack =>
        val t = head.undoStep()
        undoStack=stack
        redoStack= head::redoStack
        t
    }
  }
  def redoStep():Try[_] = {
    redoStack match {
      case Nil => Try()
      case head::stack =>
        val t = head.redoStep()
        redoStack=stack
        undoStack=head::undoStack
        t
    }
  }
}
