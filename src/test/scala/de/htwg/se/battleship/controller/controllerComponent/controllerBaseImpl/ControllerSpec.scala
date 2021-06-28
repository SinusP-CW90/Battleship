package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, Ship}
import de.htwg.se.battleship.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec._

import java.nio.file.{Files, Paths}
import scala.io.Source

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
    }
    "test to set the size of the battefield to 1" in {
      val noPlayground = new Battlefield(1)
      val controller = new Controller(noPlayground,noPlayground)
      controller.save()
      controller.load()
      //controller.battlefieldSize should be(1)
    }
    "test to set the size of the battefield to 0" in {
      val noPlayground = new Battlefield(0)
      val controller = new Controller(noPlayground,noPlayground)
      controller.save()
      controller.load()
      controller.battlefieldSize should be(0)
    }

    "have a cover case where the battlefield cannot load" in {
      val noPlayground = new Battlefield(0)
      val controller = new Controller(noPlayground,noPlayground)
      controller.save()
      controller.load()
    }
    "test his Ship trait" in {
      object FakeImpl extends Ship {
        override def swim(): Unit = println("ship is swimming Trait Test")
      }
    }
    "have a empty Battlefield with a blockSize" in {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)

      controller.blockSize should be(Math.sqrt(smallPlayground.size).toInt)
    }
    "have a cell function that shows the converted content of the cell" in {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      controller.cell(0,0).toString should be(".")
    }
    "have a function that checks whether a cell has been set" in {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      controller.statusText should be (controller.statusText)
      controller.isSet(0,0) should be(false)
    }
    "have a default case by create an Empty Battlefiled with no matched value" in {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      controller.createEmptyBattlefield(1)
      controller.battlefieldSize should be(3)
    }
    "have a function with which you can change the size of the battlefield during the game" in {
      val smallPlayground = new Battlefield(3)
      val controller = new Controller(smallPlayground,smallPlayground)
      controller.resize(6)
      controller.battlefieldSize should be(6)
    }


  }
}
