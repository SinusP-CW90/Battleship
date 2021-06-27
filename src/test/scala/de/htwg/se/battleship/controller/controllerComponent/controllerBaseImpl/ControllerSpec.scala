package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, Ship}
import de.htwg.se.battleship.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec._

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      val observer: Observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      "test Ship trait" in {
      object FakeImpl extends Ship {
        override def swim(): Unit = println("ship is swimming Trait Test")
        }
      }
      //TODO start und set test erstellen, bzw. zum laufen bringen
      /*
      "notify its Observer after start a Game" in {
        controller.start();
        //controller.set(controller.pgP1L, 1,1,4)
        observer.updated should be(true)
        controller.pgP1L.cell(0,0).value should be (0)

*/
    }
  }
}
