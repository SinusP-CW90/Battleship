package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.battleship.Battleship.controller.gameState
import de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates.{GameState, SetShipsState}
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, Ship}
import de.htwg.se.battleship.util.{Observer, UndoManager}
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
      controller.battlefieldSize should be(1)
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
      controller.currentGameState should be (controller.currentGameState)
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
    "can create a random Battlefield and it is possible for player 1 to win" in {
      val miniPlayground = new Battlefield(1)
      val controller = new Controller(miniPlayground,miniPlayground)
      controller.createRandomBattlefield("r", controller.battlefieldSize)
      controller.createRandomBattlefield("l", controller.battlefieldSize)
      controller.gameState.handle("shoot")
      controller.set("a","1")
      controller.gameState.handle("win")
      controller.battlefieldSize should be(1)
      controller.pgP2R.isWinning(controller.pgP2R)should be(true)
    }
    "can create a random Battlefield and it is possible for player 2 to win" in {
      val miniPlayground = new Battlefield(1)
      val controller = new Controller(miniPlayground,miniPlayground)
      controller.createRandomBattlefield("r", controller.battlefieldSize)
      controller.createRandomBattlefield("l", controller.battlefieldSize)
      controller.gameState.handle("shoot")
      controller.switchPlayer()
      controller.set("a","1")
      controller.gameState.handle("win")
      controller.battlefieldSize should be(1)
      controller.pgP1L.isWinning(controller.pgP1L)should be(true)
    }
    //todo - go in the other case
    "cover the case whitch noch player win the game" in {
      val miniPlayground = new Battlefield(1)
      val controller = new Controller(miniPlayground,miniPlayground)
      controller.playerSite="test"
      controller.gameState.handle("win")
      controller.playerSite should be ("test")
      controller.pgP1L.isWinning(controller.pgP1L)should be(true)
    }
    "catch if the player set a ship over his size" in {
      val miniPlayground = new Battlefield(1)
      val controller = new Controller(miniPlayground,miniPlayground)
      controller.set("Z","99")
      controller.battlefieldSize should be(1)
      controller.currentGameState should be("set Error2598")
    }
    "initialized with a size of 2" should {
      val controller = new Controller(new Battlefield(2),new Battlefield(2))
      controller.pgP1L = controller.pgP1L.set(0, 0, 1)
      controller.save()
      controller.pgP1L = controller.pgP1L.set(0, 1, 2)
      controller.load()
      "save and load in Json and have the same size and values" in {
        controller.pgP1L.size should be(2)
        controller.pgP1L.cell(0,0).value should be(1)
        controller.pgP1L.cell(0,1).toString should be(".")
      }
    }
    "initializes with different sizes" should {
      val controller = new Controller(new Battlefield(3),new Battlefield(3))
      controller.save()
      controller.load()
      "save and load in Json and have the same sizeof 3" in {
        controller.pgP1L.size should be(3)
      }
      val controller4 = new Controller(new Battlefield(4),new Battlefield(4))
      controller4.save()
      controller4.load()
      "save and load in Json and have the same sizeof 4" in {
        controller4.pgP1L.size should be(4)
      }
      val controller5 = new Controller(new Battlefield(5),new Battlefield(5))
      controller5.createEmptyBattlefield(5)
      controller5.save()
      controller5.load()
      "save and load in Json and have the same sizeof 5" in {
        controller5.pgP1L.size should be(5)
      }
      val controller6 = new Controller(new Battlefield(6),new Battlefield(6))
      controller6.save()
      controller6.load()
      "save and load in Json and have the same sizeof 6" in {
        controller6.pgP1L.size should be(6)
      }
      val controller9 = new Controller(new Battlefield(9),new Battlefield(9))
      controller9.save()
      controller9.load()
      "save and load in Json and have the same sizeof 9" in {
        controller9.pgP1L.size should be(9)
      }
    }
    "have a setCommand" should {
      val controller = new Controller(new Battlefield(2),new Battlefield(2))
      val undoManager = new UndoManager
      undoManager.doStep(new SetCommand(0,0,1, controller))
      "should do, undo and redo correctly" in {
        controller.pgP1L.cell(0,0).value should be(1)
        undoManager.undoStep()
        controller.pgP1L.cell(0,0).value should be(0)
        undoManager.redoStep()
        controller.pgP1L.cell(0,0).value should be(1)
      }
    }
    "have a PlayerShootCommand" should {
      val controller = new Controller(new Battlefield(2),new Battlefield(2))
      val undoManager = new UndoManager
      undoManager.doStep(new PlayerShootCommand("r",0,0, controller))
      "should do, undo and redo correctly" in {
        controller.pgP1L.cell(0,0).value should be(3)
        undoManager.undoStep()
        controller.pgP1L.cell(0,0).value should be(0)
        undoManager.redoStep()
        controller.pgP1L.cell(0,0).value should be(3)
      }
    }

    "have a state switch, when both player set their ships" should {
      val controller = new Controller(new Battlefield(1),new Battlefield(1))
      controller.set("a","1")
      controller.set("a","1")
      "should go to the next state" in {
        controller.currentGameState  should be("shoot")
        controller.playerSite  should be("r")
        controller.pgP1L.cell(0,0).value should be(1)
      }
    }
    "have a case in the StartState" should {
      val controller = new Controller(new Battlefield(1),new Battlefield(1))
      gameState.handle("start")
      gameState.handle("test")
      "should have a other input case" in {
        controller.currentGameState  should be("start")
      }
    }
    "have a case in the WinState" should {
      val controller = new Controller(new Battlefield(1),new Battlefield(1))
      controller.currentGameState = "win"
      gameState.handle("win")
      controller.currentGameState = "test"
      gameState.handle("test")
      "should have a other input case" in {
        controller.currentGameState  should be("test")
      }
    }
    //test for case class coverage
    "have a GameState" should {
      val controller = new Controller(new Battlefield(1),new Battlefield(1))
      val caseClassGameState = SetShipsState(controller)
      //SetShipsState.unapply(caseClassGameState).get should be("controller")
    }

  }
}
