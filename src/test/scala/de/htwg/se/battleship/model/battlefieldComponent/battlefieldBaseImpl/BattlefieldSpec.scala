package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.*
import org.scalatest.wordspec.AnyWordSpec

class BattlefieldSpec extends AnyWordSpec with Matchers {
  //TODO Test anpassen!
  "The Battlefield" when {
    //case class test for 100% Coverage
    "initialized" should {
      "have a  parameter" in {
        val caseClassPlayground = Battlefield(Matrix(Vector(Vector(Cell(1)))))
        //Battlefield.unapply(caseClassPlayground).get should be(Matrix(Vector(Vector(Cell(1)))))
      }
    }
    "initialized to size 1" should {
      val newEmptyBattlefield = new Battlefield(1)
      "have the value 0 in index [0,0]" in {
        newEmptyBattlefield.cell(0, 0) should be(Cell(0))
      }
    }
    "initialized to size 3" should {
      val newEmptyBattlefield = new Battlefield(3)
      "have the value 0 in index [2,2]" in {
        newEmptyBattlefield.cell(2, 2) should be(Cell(0))
      }
    }
    "set on index 0,0 to the value 0" should {
      val testBattlefield = Battlefield(Matrix(Vector(Vector(Cell(0)))))
      "have value 0" in {
        testBattlefield.cell(0, 0) should be(Cell(0))
      }
    }
    "set/change on index 0,0 to the value 7" should {
      val testBattlefield1 = Battlefield(Matrix(Vector(Vector(Cell(1)))))
      "have value 7" in {
        val b = testBattlefield1.set(0, 0, 7)
        b.cell(0, 0) should be(Cell(7))
      }
    }
    "set/change on index 1,1 to the value 7" should {
      val testBattlefield2 = new Battlefield(2)
      "have value 7" in {
        val b2 = testBattlefield2.set(1, 1, 7)
        b2.cell(1, 1) should be(Cell(7))
      }
    }
    "set row on index 1,2 to the value 7 with a Letter" should {
      val testBattlefield = new Battlefield(3)
      "have value 7" in {
        val b = testBattlefield.setRowWithLetter("b", "3", 7)
        b.cell(1, 2) should be(Cell(7))
      }
    }
    "have a size of 3" should {
      val testBattlefield = new Battlefield(3)
      "have on the first row 'Vector(Cell(0), Cell(0), Cell(0)'" in {
        testBattlefield.row(0) should be(Vector(Cell(0), Cell(0), Cell(0)))
      }
      "have on the first col 'Vector(Cell(0), Cell(0), Cell(0)'" in {
        testBattlefield.col(0) should be(Vector(Cell(0), Cell(0), Cell(0)))
      }
    }

    "initialized with a size of 1, which shoot is a miss on 0,0" should {
      val testBattlefield = new Battlefield(1)
      "have a 0 in 0,0 (no changes)" in {
        testBattlefield.cell(0,0).value should be(0)
        testBattlefield.shoot(testBattlefield, 0, 0) should be(Battlefield(Matrix(Vector(Vector(Cell(3))))))
      }
    }
    "initialized with a size of 1, which shoot is a hit on 0,0" should {
      val testBattlefield = new Battlefield(1)
      val testBFwithABoat = testBattlefield.set(0, 0, 1)
      "have a 2 (for hit) in 0,0" in {
        testBFwithABoat.shoot(testBFwithABoat, 0, 0) should be(Battlefield(Matrix(Vector(Vector(Cell(2))))))
        }
      }
      "initialized with a size of 1 and is NOT winning" should {
        val testBattlefield = new Battlefield(1)
        val testBFwithABoat = testBattlefield.set(0, 0, 1)
        "return a false" in {
          testBFwithABoat.isWinning(testBFwithABoat) should be(false)
        }
      }
      "initialized with a size of 1 and is winning" should {
        val testBattlefield = new Battlefield(1)
        val testBFwithABoat = testBattlefield.set(0, 0, 2)
        "return a true" in {
          testBFwithABoat.isWinning(testBFwithABoat) should be(true)
        }
      }
    "shoot a cell with value 2 (already hit)" should {
      val testBattlefield = new Battlefield(1)
      val testBFwithABoat = testBattlefield.set(0, 0, 2)
      "change the value to 2" in {
        testBFwithABoat.shoot(testBFwithABoat,0,0)
        testBFwithABoat.cell(0,0).value should be(2)
      }
    }
    "shoot a cell with value 3" should {
      val testBattlefield = new Battlefield(1)
      val testBFwithABoat = testBattlefield.set(0, 0, 3)
      "not change the value" in {
        testBFwithABoat.shoot(testBFwithABoat,0,0)
        testBFwithABoat.cell(0,0).value should be(3)
      }
    }
    "shoot a cell with a unused value" should {
      val testBattlefield = new Battlefield(1)
      val testBFwithABoat = testBattlefield.set(0, 0, 7)
      "show a ? in the battlefield" in {
        testBFwithABoat.shoot(testBFwithABoat,0,0)
        testBFwithABoat.cell(0,0).toString should be("?")
      }
    }
    }
    /*
    "BattlefieldString function" should {
      val nonEmptyBattlefieldString = Battlefield(0,0).playgroundString()
      "retrun a non empty String" in {
        nonEmptyBattlefieldString should not be empty
      }
    }
  }
*/

}
