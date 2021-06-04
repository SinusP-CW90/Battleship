package de.htwg.se.battleship.util

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

import scala.util.Try

class incrCommand extends Command {
  var state:Int =0
  override def doStep(): Try[_] = Try ( state+=1)

  override def undoStep: Try[_] = Try (state-=1)

  override def redoStep: Try[_] = Try (state+=1)
}

class CommandSpec extends AnyWordSpec with Matchers {
  "A Command" should {

    "have a do step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.doStep
      command.state should be(2)

    }
    "have an undo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.undoStep
      command.state should be(0)
    }
    "have a redo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.undoStep
      command.state should be(0)
      command.redoStep
      command.state should be(1)
    }
  }
}