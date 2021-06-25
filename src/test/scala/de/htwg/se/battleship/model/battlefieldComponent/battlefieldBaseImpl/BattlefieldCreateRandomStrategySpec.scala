package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BattlefieldCreateRandomStrategySpec extends AnyWordSpec with Matchers {
  "A GridCreator " should {
    "create an empty Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = (new BattlefieldCreateRandomStrategy).createNewGrid(1)
      tinyGrid.cell(0,0).value should be(1)
    }
    "if the cell ist already set, fill another cell" in {
      var tinyGrid = (new BattlefieldCreateRandomStrategy).createNewGrid(2)
      for {
        times <- 0 to 10

      } tinyGrid = (new BattlefieldCreateRandomStrategy).createNewGrid(2)
      //tinyGrid.cell(0,0).value should be(1)
    }
  }

}
