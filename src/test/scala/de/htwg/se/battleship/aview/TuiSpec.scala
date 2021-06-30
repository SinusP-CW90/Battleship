package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class TuiSpec extends AnyWordSpec with Matchers {
  "A SinkingShip Tui" should {
    val controller = new Controller(new Battlefield(6),new Battlefield(6))
    val tui = new Tui(controller)

    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    "create and print Player Names on input 'set'" in {
      tui.processInputLine("set")
      //
    }
    "create a empty Battlefield on input 'mini'" in {
      tui.processInputLine("mini")
      controller.pgP1L should be(new Battlefield(2))
      controller.pgP2R should be(new Battlefield(2))
    }
    "create a empty Battlefield on input 's'" in {
      tui.processInputLine("s")
      controller.pgP1L should be(new Battlefield(3))
      controller.pgP2R should be(new Battlefield(3))
    }
    "create a empty Battlefield on input 'tiny'" in {
      tui.processInputLine("tiny")
      controller.pgP1L should be(new Battlefield(4))
      controller.pgP2R should be(new Battlefield(4))
    }
    "create a empty Battlefield on input 'm'" in {
      tui.processInputLine("m")
      controller.pgP1L should be(new Battlefield(6))
      controller.pgP2R should be(new Battlefield(6))
    }

    "create and empty Battlefield on input 'l'" in {
      tui.processInputLine("l")
      controller.pgP1L should be(new Battlefield(9))
      controller.pgP2R should be(new Battlefield(9))
    }
    "create and empty Battlefield on input 'start'" in {
      tui.processInputLine("start")
    }
    "do nothing on bad input like'99999'" in {
      val old = controller.playgroundToString
      tui.processInputLine("X")
      controller.playgroundToString should be(old)
    }
    "to test is winning" in {
      tui.processInputLine("A1")
      tui.processInputLine("sA1")
    }
    "input test to set the cells (left Player and right Player) with undo redo" in {
      tui.processInputLine("a1")
      tui.processInputLine("a2")
      tui.processInputLine("b1")
      tui.processInputLine("b2")
      tui.processInputLine("undo")
      tui.processInputLine("redo")
      tui.processInputLine("A1")
      tui.processInputLine("A2")
      tui.processInputLine("B1")
      tui.processInputLine("B2")
      tui.processInputLine("undo")
      tui.processInputLine("redo")
      tui.processInputLine("sA1")
      tui.processInputLine("sA2")
      tui.processInputLine("sB1")
      tui.processInputLine("sB2")
      tui.processInputLine("001")

      tui.processInputLine("111")
      tui.processInputLine("undo")
      tui.processInputLine("redo")
      tui.processInputLine("save")
      tui.processInputLine("load")
    }
    "input test to create some boats" in {
      tui.processInputLine("msw")
      tui.processInputLine("lsw")
      tui.processInputLine("sw")
    }
    "input test to create a random Battlefield on the Left Side" in {
      tui.processInputLine("rl")
    }
    "input test to create a random Battlefield on the Right Side" in {
      tui.processInputLine("rr")
    }
    "input test to create a random Battlefield for both sides" in {
      tui.processInputLine("rrr")
    }
    "input test to switch the current Player" in {
      tui.processInputLine("sp")
    }

    "input test for a GUI funktion" in {
      tui.processInputLine("setL")
    }



  }
}
