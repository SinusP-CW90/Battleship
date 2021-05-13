package de.htwg.se.battleship.model

import scala.io.StdIn.readLine
import scala.math.sqrt

case class Battlefield(cells:Matrix[Cell]){
  //set the size with the Matrix class
  def this(size:Int) = this(new Matrix[Cell](size, Cell(0)))
  //Int value with the size of the Battlefield
  val size:Int = cells.size

  val blocknum: Int = sqrt(size).toInt

   def toStringX: String = {
    val lineseparator = ("+-" + ("--" * blocknum)) * blocknum + "+\n"
    val line = ("| " + ("x " * blocknum)) * blocknum + "|\n"
    var box = "\n" + (lineseparator + (line * blocknum)) * blocknum + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } box = box.replaceFirst("x", cell(row, col).toString)
    box
  }

  //return the called cell
  def cell(row:Int, col:Int):Cell = cells.cell(row, col)
  //change the cell value in the Battlefield
  def set(row:Int, col:Int, value:Int):Battlefield = copy(cells.replaceCell(row, col, Cell(value)))

  def setRowWithLetter(rowString:String, columnString:String, value:Int):Battlefield = {
    val UpperRowString = rowString.toUpperCase
    val row = (UpperRowString(0) -65).toChar.toInt
    val col = columnString.toInt
    copy (cells.replaceCell (row, col-1, Cell (value)))
  }
  //show the called row of the Battlefield
  def row(row:Int):Vector[Cell] = cells.rows(row)
  //show the called column of the Battlefield
  def col(col:Int):Vector[Cell] = cells.rows.map(row=>row(col))

  //TODO - prints raus nehemen
  def shoot(pg: Battlefield, row:Int, col:Int):Battlefield = {
    if (cell(row,col).value == 1){
      println("hit")
      this.set(row,col, 2)
    }
    else {
      println("miss")
      pg
    }
  }
  def isWinning(pg: Battlefield): Boolean ={
    var isWinning = true
    for (x <- 1 to pg.size) {
      for (y <- 1 to pg.size) {
        if(pg.cell(x-1,y-1).value == 1){
          println("found a 1 in " +x +" " +y)
          isWinning =  false
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

  //generate a String that represents the Battlefield
  def playgroundString(playgroundLeft:Battlefield, playgroundRight:Battlefield): String = {

    def createNumberRow(): String ={
      var currentNumberString = ""
      for (x <- 1 to playgroundLeft.size) {
        currentNumberString = currentNumberString.concat(x.toString+"  ")
      }
      val numberString = "\n    " +currentNumberString + "|  " +currentNumberString
      numberString
    }

    def battlefieldToString():String={
      //val y = createNumberRow();
      val line = ("L |" + (" q " * playgroundLeft.size)) + " |" + ("  Q" * playgroundRight.size) +" | R\n"
      var box = createNumberRow()+"\n" +"" + (line * playgroundLeft.size)
      val playerNameString =((" " * (playgroundLeft.size-1))+"Player 1" +(" " * (playgroundLeft.size-1)) +" |" )
      for {
        row <- 0 until playgroundLeft.size
        col <- 0 until playgroundLeft.size
      } box = (box.replaceFirst("q", playgroundLeft.cell(row, col).toString)
        replaceFirst("Q", playgroundRight.cell(row, col).toString)
        replaceFirst("L", ("A"(0) + col).toChar.toString)
        replaceFirst("R", ("A"(0) + col).toChar.toString))
      box
    }

    def colorBattlefield(battlefield: String): String ={
      val cB= battlefield.replace(" . ","\u001b[48;5;20m . \u001b[0m")
      cB
    }

    colorBattlefield(battlefieldToString())
  }
}