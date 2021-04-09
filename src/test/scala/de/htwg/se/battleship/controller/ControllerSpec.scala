package de.htwg.se.battleship.controller

import de.htwg.se.battleship.model.Playground
import de.htwg.se.battleship.util.Observer

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val smallPlayground = new Playground(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyPlayground(3)
        observer.updated should be(true)
        controller.pgP1L.size should be(3)
        controller.pgP2R.size should be(3)
      }
      /*
      "notify its Observer after setting a cell" in {
        controller.set(controller.pgP1L, 1,1,4)
        controller.set(controller.pgP1L, 0,0,4)
        observer.updated should be(true)
        controller.pgP1L.cell(1,1).value should be (4)
      }
       */
    }
  }
}
