import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, Cell, Matrix}

val grid = new Battlefield(4)
grid.row(0)

//generate a String that represents the Battlefield
def playgroundString(pgP1:Battlefield, pgP2:Battlefield, currentPlayer: String): String = {

  //create a number String for the first line of the Battlefield
  //(allows different playground sizes for P1 and P2)
  def buildNumberRowString(): String ={
    var numberString = "\n"
    var currentNumberP1 = "  "
    var currentNumberP2 = "  "
    for (x <- 1 to pgP1.size) {
      currentNumberP1 = currentNumberP1.concat(x.toString + "  ")
    }
    numberString = numberString.concat(currentNumberP1 + "|")
    for (x <- 1 to pgP2.size) {
      currentNumberP2 = currentNumberP2.concat(x.toString + "  ")
    }
    numberString.concat(currentNumberP2)
  }

  //check if the cell ist set to 0 or to another value (that changes the String)
  def setRowForPlayer(x:Int, y:Int, pg:Battlefield, lineString: String): String ={

    var currentCell = lineString

    if(pg.cell(x-1,y-1).value == 0){
      currentCell = currentCell.concat("\u001b[48;5;20m . \u001b[0m")
    }
    if(pg.cell(x-1,y-1).value == 1) {
      currentCell = currentCell.concat(" x ")
    }
    if(pg.cell(x-1,y-1).value == 2) {
      currentCell = currentCell.concat(" o ")
    }
    currentCell
  }

  def setRowForEnemy(x:Int, y:Int, pg:Battlefield, lineString: String): String ={
    var currentCell = lineString
    if(pg.cell(x-1,y-1).value < 2){
      currentCell = currentCell.concat("\u001b[48;5;20m . \u001b[0m")
    }
    if(pg.cell(x-1,y-1).value == 2) {
      currentCell = currentCell.concat(" o ")
    }
    currentCell
  }
  //creates a String for the Battlefield
  def buildRows(): String ={
    var playgroundString = ""
    var lineP1 = ""
    var lineP2 = ""
    //First loop creates the column Strings
    for (x <- 1 to pgP1.size) {
      //Set first Letter
      playgroundString = playgroundString.concat(("A"(0) + x - 1).toChar.toString)
      //loop to create the row Strings
      for (y <- 1 to pgP1.size) {

        //build the Battlefield row Strings from the cell values for player1 and player 2
        if (currentPlayer == "p1"){
          lineP1 = setRowForPlayer(x,y,pgP1,lineP1)
          lineP2 = setRowForEnemy(x,y,pgP2,lineP2)
        }
        if (currentPlayer == "p2"){
          lineP1 = setRowForEnemy(x,y,pgP1,lineP1)
          lineP2 = setRowForPlayer(x,y,pgP2,lineP2)
        }
      }
      playgroundString = playgroundString.concat(lineP1 +" | " +lineP2 +("A"(0) + x - 1).toChar.toString+"\n")
      lineP1=""
      lineP2=""
    }
    playgroundString
  }
  "%s\n%s".format(buildNumberRowString(), buildRows())
}

val testBattlefield = Battlefield(Matrix(Vector(Vector(Cell(1)))))
val b2= testBattlefield.set(0,0,7)
b2.cell(0,0)
