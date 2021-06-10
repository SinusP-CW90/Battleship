
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, Cell}

import java.io.ByteArrayInputStream

var testBattlefield1 = new Battlefield(2)
val testCell = Cell(0)
val testCell1 = Cell(1)
val testCell2 = Cell(2)
var testBattlefield = testBattlefield1.set(1,1,7)
var testBattlefieldR = testBattlefield1.set(0,0,7)


object Singleton {
  def singletonFunction:Unit = println("I am a singleton")
}

Singleton.singletonFunction