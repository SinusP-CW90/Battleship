package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller.Controller
import de.htwg.se.battleship.model.Playground

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class TuiSpec extends AnyWordSpec with Matchers {
  "A SinkingShip Tui" should {
    val controller = new Controller(new Playground(6),new Playground(6))
    val tui = new Tui(controller)

    "create and empty Playground on input 'm'" in {
      tui.processInputLine("m")
      controller.pgP1L should be(new Playground(6))
      controller.pgP2R should be(new Playground(6))
    }
    "create and empty Playground on input 's'" in {
      tui.processInputLine("s")
      controller.pgP1L should be(new Playground(3))
      controller.pgP2R should be(new Playground(3))
    }
    "create and empty Playground on input 'l'" in {
      tui.processInputLine("l")
      controller.pgP1L should be(new Playground(9))
      controller.pgP2R should be(new Playground(9))
    }
/*
    "start the Game" in {
      tui.processInputLine("s")
      controller.start();
      //auf rückgabe warten, um zu antworten
      tui.processInputLine("a1")
      //controller.pgP1L.cell(0,0).value should be(2)
    }
     */
  }
}
