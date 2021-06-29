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

  var currentPlayer: Option[Player] = None

  var gameState: GameState = battleshipGameStates.GameState(this)
      gameState.handle("start")
  var currentGameState: String = "start"
  var players: Vector[Player] = Vector.empty
  var shipsToSetP1:Int = pgP1L.size
  var shipsToSetP2:Int = pgP2R.size
  var playerSite: String = "l"
  var shootValue:Int =1

  def battlefieldSize:Int = pgP1L.size
  def blockSize:Int = Math.sqrt(pgP1L.size).toInt

  //TODO state
  def statusText:String = this.gameState.state.toString
  def setPlayerNames(): String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.battlefieldString(pgP1L, pgP2R)
  def switchPlayer():Unit={
    if (playerSite=="l"){playerSite="r"}
    else if (playerSite=="r"){playerSite="l"}
    //--->test
    println("Now Player: "+playerSite)
  }

  //übernhame
  def cell(row:Int, col:Int): CellInterface = pgP2R.cell(row,col)

  def isSet(row:Int, col:Int):Boolean = pgP1L.cell(row, col).isSet

  def createEmptyBattlefield(size: Int):Unit = {
    size match {
      case 2 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-mini"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-mini"))
      case 3 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-tiny"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-tiny"))
      case 4 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-small"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-small"))
      case 6 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-normal"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-normal"))
      case 9 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-big"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-big"))
      case _ =>
    }
    publish(new CellChanged)
  }

  def resize(newSize:Int) :Unit = {
    pgP1L = new Battlefield(newSize)
    pgP2R = new Battlefield(newSize)
    gameState.handle("resize")
    publish(BattlefieldSizeChanged(newSize))
    publish(new CellChanged)
  }

  def start(input: String): Boolean = {
    gameState.handle(input)
    //notifyObservers
    true
  }
 def createShip(shiptype: String){
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
      playerSite match {
        case "l" => if(pgP2R.isWinning(pgP2R)){gameState.handle("win")}
        case "r" => if(pgP1L.isWinning(pgP1L)){gameState.handle("win")}
      }
      publish(new CellChanged)
    }
    if ((currentGameState == "setShips")|(currentGameState == "start")) {
      if (checkIsInRange(row: Int, col: Int)) {
        undoManager.doStep(new PlayerSetCommand(playerSite, row, col, 1, this))
        gameState.handle("setShips")
        publish(new CellChanged)
      }
      else
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

  def set(player:String,row: Int, col: Int, value: Int):Unit = {
    undoManager.doStep(new PlayerSetCommand(playerSite, row, col, value, this))
    gameState.handle("setShips")
    publish(new CellChanged)
  }
  def setL(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameState.handle("setShips")
    //pgP2R.isWinning(pgP2R)
    publish(new CellChanged)
    //notifyObservers
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
    //publish(new CellChanged)
  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    gameState.handle("random")
    if (player == "l") pgP1L = randomStrategy.createNewGrid(size)
    if (player == "r") pgP2R = randomStrategy.createNewGrid(size)
    publish(new CellChanged)
    //notifyObservers
  }
}