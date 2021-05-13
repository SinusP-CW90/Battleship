import de.htwg.se.battleship.model
import de.htwg.se.battleship.model._

var testBattlefield1 = new Battlefield(2)
val testCell = new Cell(0)
val testCell1 = new Cell(1)
val testCell2 = new Cell(2)
var testBattlefield = testBattlefield1.set(1,1,7)
var testBattlefieldR = testBattlefield1.set(0,0,7)

def createNumberRow(testBattlefield: Battlefield): String ={
  var currentNumberString = ""
  for (x <- 1 to testBattlefield.size) {
    currentNumberString = currentNumberString.concat(x.toString+"  ")
  }
  val numberString = "\n    " +currentNumberString + "|  " +currentNumberString
  numberString
}

def battlefieldToString(battlefieldLeft: Battlefield, BattlefieldRight: Battlefield):String={
  val y = createNumberRow(testBattlefield);
  val line = ("L |" + (" q " * battlefieldLeft.size)) + " |" + ("  Q" * BattlefieldRight.size) +" | R\n"
  var box = y+"\n" +"" + (line * battlefieldLeft.size)
  val playerNameString =((" " * (battlefieldLeft.size-1))+"Player 1" +(" " * (battlefieldLeft.size-1)) +" |" )
  for {
    row <- 0 until battlefieldLeft.size
    col <- 0 until battlefieldLeft.size
  } box = (box.replaceFirst("q", battlefieldLeft.cell(row, col).toString)
    replaceFirst("Q", BattlefieldRight.cell(row, col).toString)
    replaceFirst("L", ("A"(0) + col).toChar.toString)
    replaceFirst("R", ("A"(0) + col).toChar.toString))
  box
}

def colorBattlefield(battlefield: String): String ={
  val cB= battlefield.replace(" . ","\u001b[48;5;20m . \u001b[0m")
  cB
}


var b = battlefieldToString(testBattlefield, testBattlefieldR)
var x = colorBattlefield(b)
println("tes1t")
