package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.battleship.BattleshipModule
import de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates.GameState
import de.htwg.se.battleship.controller.controllerComponent.{BattlefieldSizeChanged, ControllerInterface, battleshipGameStates}
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, BattlefieldCreateRandomStrategy, Ship}
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface
import de.htwg.se.battleship.model.playerComponent.Player
import de.htwg.se.battleship.util.UndoManager

import scala.swing.Publisher

class Controller @Inject() (@Named("DefaultSize") var pgP1L :BattlefieldInterface, @Named("DefaultSize") var pgP2R: BattlefieldInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager
  val injector: Injector = Guice.createInjector(new BattleshipModule)
  val fileIo: FileIOInterface = injector.instance[FileIOInterface]
  var randomStrategy: BattlefieldCreateRandomStrategy = injector.instance[BattlefieldCreateRandomStrategy]

  var gameState: GameState = battleshipGameStates.GameState(this)
  var currentGameState: String = "start"
      gameState.handle("start")
  var player: Vector[Player] = Vector.empty
  var shipsToSetP1:Int = pgP1L.size
  var shipsToSetP2:Int = pgP2R.size
  var playerSite: String = "l"

  def battlefieldSize:Int = pgP1L.size
  def blockSize:Int = Math.sqrt(pgP1L.size).toInt
  def setPlayerNames(): String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.battlefieldString(pgP1L, pgP2R)
  //def playgroundToStringWOColor: String = pgP1L.battlefieldStringWOColor(pgP1L, pgP2R)
  def switchPlayer:Unit = {
    playerSite match {
      case "l" => playerSite="r"
      case "r" => playerSite="l"
    }
    println("Now Player: "+playerSite)
  }

  def cell(row:Int, col:Int): CellInterface = pgP2R.cell(row,col)
  def cellL(row:Int, col:Int): CellInterface = pgP1L.cell(row,col)

  def isSet(row:Int, col:Int):Boolean = pgP1L.cell(row, col).isSet
  def isSetR(row:Int, col:Int):Boolean = pgP2R.cell(row, col).isSet

  def createEmptyBattlefield(size: Int):Unit = {
    size match {
      case 2 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-2x2"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-2x2"))
      case 3 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-3x3"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-3x3"))
      case 4 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-4x4"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-4x4"))
      case 5 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-5x5"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-5x5"))
      case 6 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-6x6"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-6x6"))
      case 9 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-9x9"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-9x9"))
      case _ =>
    }
    publish(BattlefieldSizeChanged(size))
  }

  def resize(newSize:Int) :Unit = {
    pgP1L = new Battlefield(newSize)
    pgP2R = new Battlefield(newSize)
    gameState.handle("resize")
    publish(BattlefieldSizeChanged(newSize))
    publish(GridSizeChanged(newSize))
    //publish(CellChanged)
  }

  def start(input: String): Boolean = {
    gameState.handle(input)
    true
  }

 def createShip(shiptype: String): Unit = {
    val ship = Ship(shiptype)
    ship.swim()
  }

  def set(rowString: String, colString: String):Unit = {
    //test
    println("gameINFO: in setMethode")
    println("currentGameState: " + currentGameState)
    println("shipsToSetP1: " + shipsToSetP1)
    println("current Player = " + playerSite)

    val UpperRowString = rowString.toUpperCase
    val row = (UpperRowString(0) - 65).toChar.toInt
    val col = colString.toInt - 1

    if (currentGameState == "shoot") {
      undoManager.doStep(new PlayerShootCommand(playerSite, row, col, this))
      gameState.handle("shoot")
      playerIsWinning()
      publish(new CellChanged)
    }
    if ((currentGameState == "setShips")|(currentGameState == "start")) {
      if (checkIsInRange(row: Int, col: Int)) {
        undoManager.doStep(new PlayerSetCommand(playerSite, row, col, 1, this))
        gameState.handle("setShips")
        publish(new CellChanged)
      }
      else
        //currentGameState = "set Error" + row + col
        gameState.handle("set Error" + row + col)
    }
  }

  def checkIsInRange(row: Int, col: Int): Boolean = {
    if ((row < battlefieldSize) &&(col < battlefieldSize)) {
      println(row +" gz:"+battlefieldSize)
      true
    }
    else false
  }

  def playerIsWinning():Unit ={
    playerSite match {
      case "l" => if (pgP2R.isWinning(pgP2R)) {
        gameState.handle("win")
        start("win")
      }
      case "r" => if (pgP1L.isWinning(pgP1L)) {
        gameState.handle("win")
      }
    }
  }

  def setL(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameState.handle("setShips")
    pgP2R.isWinning(pgP2R)
    publish(new CellChanged)
    //notifyObservers
  }

  def setInGUI(playerSite:String, row: Int, col: Int): Unit = {
    if (checkIsInRange(row: Int, col: Int)) {
      undoManager.doStep(new PlayerSetCommand(playerSite, row, col, 1, this))
      gameState.handle("setShips")
      pgP2R.isWinning(pgP2R)
      publish(new CellChanged)
    }
  }

  def undo(): Unit = {
    undoManager.undoStep()
    gameState.handle("UNDO")
    publish(new CellChanged)
    //notifyObservers
  }

  def redo(): Unit = {
    undoManager.redoStep()
    gameState.handle("REDO")
    publish(new CellChanged)
    //notifyObservers
  }
  def save(): Unit = {
    fileIo.save("battlefiledP1",pgP1L)
    fileIo.save("battlefiledP2",pgP2R)
    gameState.handle("SAVED")
    publish(new CellChanged)
  }

  def load(): Unit = {
    val pgP1LOption = fileIo.load("battlefiledP1")
    val pgP2ROption = fileIo.load("battlefiledP2")
    pgP1LOption match {
      case None =>
        createEmptyBattlefield(battlefieldSize)
        gameState.handle("COULDNOTLOADp1")
      case Some(_battlefiled) =>
        pgP1L = _battlefiled
        gameState.handle("LOADEDp1")
    }
    pgP2ROption match {
      case None =>
        createEmptyBattlefield(battlefieldSize)
        gameState.handle("COULDNOTLOADp2")
      case Some(_battlefiled) =>
        pgP2R = _battlefiled
        gameState.handle("LOADEDp2")
    }
    publish(BattlefieldSizeChanged(pgP1LOption.size))
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    gameState.handle("random")
    if (player == "l") pgP1L = randomStrategy.createNewGrid(size)
    if (player == "r") pgP2R = randomStrategy.createNewGrid(size)
    publish(new CellChanged)
    //notifyObservers
  }
}