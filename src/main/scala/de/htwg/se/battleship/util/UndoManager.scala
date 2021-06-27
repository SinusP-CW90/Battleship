package de.htwg.se.battleship.util

import scala.util.Try

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil

  def doStep(command: Command):Unit = {
    undoStack = command::undoStack
    command.doStep()
  }
  def undoStep(): Unit  = {
    undoStack match {
      case  Nil =>
      case head::stack =>
        val t = head.undoStep()
        undoStack=stack
        redoStack= head::redoStack
        t
    }
  }
  def redoStep():Unit = {
    redoStack match {
      case Nil =>
      case head::stack =>
        val t = head.redoStep()
        redoStack=stack
        undoStack=head::undoStack
        t
    }
  }
}
