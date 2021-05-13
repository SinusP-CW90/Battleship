import de.htwg.se.battleship.model
import de.htwg.se.battleship.model._

import java.io.ByteArrayInputStream

var testBattlefield1 = new Battlefield(2)
val testCell = new Cell(0)
val testCell1 = new Cell(1)
val testCell2 = new Cell(2)
var testBattlefield = testBattlefield1.set(1,1,7)
var testBattlefieldR = testBattlefield1.set(0,0,7)


  val input = "Hello\nWorld\n"
  val is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
ConsoleAction.readInput(is).size===2
}