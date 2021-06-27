package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import com.google.inject.name.{Named, Names}
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.battleship.BattleshipModule
import de.htwg.se.battleship.controller.controllerComponent
import de.htwg.se.battleship.controller.controllerComponent.{ControllerInterface, GameState}
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
  var gameState: GameState = GameState(this)
  var players: Vector[Player] = Vector.empty
  //var message: Message = EmptyMessage


  def battlefieldSize:Int = pgP1L.size
  def blockSize:Int = Math.sqrt(pgP1L.size).toInt

  //TODO mal schauen obs klappt
  def statusText:String = this.gameState.state.toString
  def setPlayerNames(): String = Player().playerNamesToString(Player().setDefaultPlayerNames())
  def playgroundToString: String = pgP1L.battlefieldString(pgP1L, pgP2R)

  //übernhame
  def cell(row:Int, col:Int): CellInterface = pgP2R.cell(row,col)
  //def available(row:Int, col:Int):Set[Int] = pgP1L.available(row, col)

  //def isGiven(row: Int, col: Int):Boolean = pgP1L.cell(row, col).given
  def isSet(row:Int, col:Int):Boolean = pgP1L.cell(row, col).isSet
  //def available(row:Int, col:Int):Set[Int] = pgP1L.available(row, col)

  def createEmptyBattlefield(size: Int):Unit = {
    size match {
      case 2 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-mini"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-mini"))
      case 3 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-tiny"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-tiny"))
      case 4 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-small2"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-small2"))
      case 6 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-normal"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-normal"))
      case 9 => pgP1L = injector.instance[BattlefieldInterface](Names.named("p1-big"))
                pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-big"))
      case _ =>
    }
    /*
    pgP1L =new Battlefield(size)
    pgP2R = new Battlefield(size)
     */
    publish(new CellChanged)
    //notifyObservers
  }

  def resize(newSize:Int) :Unit = {
    pgP1L = new Battlefield(newSize)
    pgP2R = new Battlefield(newSize)
    gameState.handle("resize")
    publish(GridSizeChanged(newSize))
    publish(new CellChanged)
    //notifyObservers
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

  def set(player:String,row: Int, col: Int, value: Int):Unit = {
    undoManager.doStep(new PlayerSetCommand(player, row, col, value, this))
    gameState.handle("play")
    publish(new CellChanged)
    //notifyObservers
  }

  def setL(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameState.handle("play")
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
  def save: Unit = {
    fileIo.save("battlefiledP1",pgP1L)
    fileIo.save("battlefiledP2",pgP2R)
    gameState.handle("SAVED")
    publish(new CellChanged)
  }
/*
  def load: Unit = {
    pgP1L = fileIo.load("battlefiledP1")
    pgP2R = fileIo.load("battlefiledP2")
    gameState.handle("LOADED")
    publish(new CellChanged)
  }
*/
  def load: Unit = {
    val pgP1LOption = fileIo.load("battlefiledP1")
    val pgP2ROption = fileIo.load("battlefiledP2")
    pgP1LOption match {
      case None =>
        createEmptyBattlefield(battlefieldSize)
        gameState.handle("COULDNOTLOAD")
      case Some(_battlefiled) =>
        pgP1L = _battlefiled
        gameState.handle("LOADED")
    }
    pgP2ROption match {
      case None =>
        createEmptyBattlefield(battlefieldSize)
        gameState.handle("COULDNOTLOAD")
      case Some(_battlefiled) =>
        pgP2R = _battlefiled
        gameState.handle("LOADED")
    }
    publish(GridSizeChanged(pgP1LOption.size))
    publish(new CellChanged)

  }

  def createRandomBattlefield(player:String,size: Int): Unit = {
    gameState.handle("random")
    if (player == "l") pgP1L = randomStrategy.createNewGrid(size)
    if (player == "r") pgP2R = randomStrategy.createNewGrid(size)
    publish(new CellChanged)
    //notifyObservers
  }
  //override def gameStatus: GameState = ???
}