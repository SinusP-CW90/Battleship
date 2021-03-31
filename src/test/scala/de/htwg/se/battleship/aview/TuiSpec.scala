package de.htwg.se.battleship.aview

import de.htwg.se.battleship.controller.Controller
import de.htwg.se.battleship.model.Playground

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class TuiSpec extends AnyWordSpec with Matchers {
  "A SinkingShip Tui" should {
    val controller = new Controller(new Playground(6),new Playground(6))
    val tui = new Tui(controller)
    "create and empty Playground on input 'n'" in {
      tui.processInputLine("n")
      controller.pgP1L should be(new Playground(6))
      controller.pgP2R should be(new Playground(6))
    }
    "set a cell on input 'a1'" in {
      controller.start()
      //tui.processInputLine("start")
      tui.processInputLine("a1")
      controller.pgP1L.cell(0,0).value should be(2)
    }
  }
}
