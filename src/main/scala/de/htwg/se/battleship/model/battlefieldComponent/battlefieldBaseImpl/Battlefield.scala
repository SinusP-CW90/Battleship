package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

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

  /**With set you can change the value in the desired cell of the Battlefield - all with Int Values*/
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

  def shoot(pg: BattlefieldInterface, row: Int, col: Int): BattlefieldInterface = {
    pg.cell(row, col).value match {
      case 0 => println("miss")
                pg.set(row, col, 3)
      case 1 => println("hit")
                pg.set(row, col, 2)
      case 2 => println("miss")
                pg.set(row, col, 2)
      case 3 => println("missOverMiss")
                pg.set(row, col, 3)
      case _ => println("unknown: " +pg.cell(row, col).value)
                pg
    }
  }

  /**isWinning searches in the battlefield for still available ships (1 values in teh cells)*/
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
/*
    /**colorBattlefield colors the playing area blue, which is supposed to symbolize the water */
    def colorBattlefield(battlefield: String): String = {
      val cB = battlefield.replace(" . ", "\u001b[48;5;20m . \u001b[0m")
      cB
    }

    colorBattlefield(battlefieldToString())

 */
    battlefieldToString()
  }


  //new
  implicit val cellWrites: Writes[Cell] = (cell: CellInterface) => Json.obj(
    "value" -> cell.value
  )

  def toJson:JsValue = {
    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(size),
        "cells" -> Json.toJson(
          for {row <- 0 until size;
               col <- 0 until size} yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(cell(row, col)))
          }
        )
      )
    )
  }

}