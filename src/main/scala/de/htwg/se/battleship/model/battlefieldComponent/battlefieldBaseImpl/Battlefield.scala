package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface

/**The class Battlefield represents the Playground of one Player.
 * @param cells The cells value is a 2 dimensional Vector of all cells.
 *              Each one has a value that is used to show what happened to this cell.
 *              e.g .: 0 for nothing. 1 for ship placed, 2 for ship hit etc.
 */
case class Battlefield(cells: Matrix[Cell]) extends BattlefieldInterface {

  /**initialized the 2D Vector of Cells with the int value
   * @param size the size of the 2D Vector */
  def this(size: Int) = this(new Matrix[Cell](size, Cell(0)))

  /**Int value with the size of the Battlefield*/
  val size: Int = cells.size

  /**The cell function can be used to address the desired cell in the battlefield*/
  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  /**With Set you can change the value in the desired cell of the Battlefield - all with Int Values*/
  def set(row: Int, col: Int, value: Int): Battlefield = copy(cells.replaceCell(row, col, Cell(value)))

  /**setRowWithLetter changes the value of a desired cell. Here the row is addressed with a letter and converts it into a number*/
  def setRowWithLetter(rowString: String, columnString: String, value: Int): BattlefieldInterface = {
    val UpperRowString = rowString.toUpperCase
    val row = (UpperRowString(0) - 65).toChar.toInt
    val col = columnString.toInt
    copy(cells.replaceCell(row, col - 1, Cell(value)))
  }

  /**row gives you the desired row in the battlefield*/
  def row(row: Int): Vector[Cell] = cells.rows(row)

  /**col gives you the desired collum in the battlefield*/
  def col(col: Int): Vector[Cell] = cells.rows.map(row => row(col))

  //TODO - prints raus nehemen
  def shoot(pg: BattlefieldInterface, row: Int, col: Int): BattlefieldInterface = {
    if (cell(row, col).value == 1) {
      println("hit")
      this.set(row, col, 2)
    }
    else {
      println("miss")
      pg
    }
  }
//TODO
  def isWinning(pg: BattlefieldInterface): Boolean = {
    var isWinning = true
    for (x <- 1 to pg.size) {
      for (y <- 1 to pg.size) {
        if (pg.cell(x - 1, y - 1).value == 1) {
          println("found a 1 in " + x + " " + y)
          isWinning = false
        }
      }
    }
    isWinning
  }

  /*
  //TODO!! komplett überarbeiten, da readLine() nur in TUI oder Battleship main, wegen besserer testbarkeit vorkommen darf
//TODO fehler abfangen, wenn ships doppelt oder außerhalb der spielfeld größe gesetzt werden
  def setShips(pgP1: Battlefield, pgP2:Battlefield, currentPlayer:String): Battlefield ={
    var pgP1L = pgP1
    var pgP2R = pgP2
    var counter = size
    print("please set " +size +" Ships\n")
    var input: String = ""
    do {
      input = readLine()
      pgP1L = pgP1L.setRowWithLetter(input(0).toString,input(1).toString,1)
      counter = counter -1
      println(pgP1L.playgroundString(pgP1L,pgP2,currentPlayer))
      print(counter +" Ships are left\n")
    } while (counter != 0)
    if(currentPlayer =="p1"){
      pgP1L
    }
    else{
      pgP2R
    }
  }

//TODO - nicht gut zu testen wegen setShips
  def start(pgP1: Battlefield, pgP2:Battlefield): Battlefield ={

    setShips(pgP1,pgP2,"p1")
    setShips(pgP2,pgP1,"p2")
    pgP1
  }
*/

  /**battlefieldString creates a string that represents the battlefield for TUI*/
  def battlefieldString(playgroundLeft: BattlefieldInterface, playgroundRight: BattlefieldInterface): String = {

    /**createNumberRow creates a series of ascending numbers for the coordinates of the battlefield */
    def createNumberRow(): String = {
      var currentNumberString = ""
      for (x <- 1 to playgroundLeft.size) {
        currentNumberString = currentNumberString.concat(x.toString + "  ")
      }
      val numberString = "\n    " + currentNumberString + "|  " + currentNumberString
      numberString
    }
/**battlefieldToString converts the cell values of the 2D vector into a string and puts them in the right form */
    def battlefieldToString(): String = {
      //val y = createNumberRow();
      val line = ("L |" + (" q " * playgroundLeft.size)) + " |" + ("  Q" * playgroundRight.size) + " | R\n"
      var box = createNumberRow() + "\n" + "" + (line * playgroundLeft.size)
      val playerNameString = (" " * (playgroundLeft.size - 1)) + "Player 1" + (" " * (playgroundLeft.size - 1)) + " |"
      for {
        row <- 0 until playgroundLeft.size
        col <- 0 until playgroundLeft.size
      } box = (box.replaceFirst("q", playgroundLeft.cell(row, col).toString)
        replaceFirst("Q", playgroundRight.cell(row, col).toString)
        replaceFirst("L", ("A"(0) + col).toChar.toString)
        replaceFirst("R", ("A"(0) + col).toChar.toString))
      box
    }

    /**colorBattlefield colors the playing area blue, which is supposed to symbolize the water */
    def colorBattlefield(battlefield: String): String = {
      val cB = battlefield.replace(" . ", "\u001b[48;5;20m . \u001b[0m")
      cB
    }

    colorBattlefield(battlefieldToString())
  }
}

/*
/**Battlefield Object to write and read the cells into Json */
object Battlefield {
  import play.api.libs.json._
  implicit val battlefieldWrites: OWrites[Battlefield] = Json.writes[Battlefield]
  implicit val battlefieldReads: Reads[Battlefield] = Json.reads[Battlefield]
}
*/