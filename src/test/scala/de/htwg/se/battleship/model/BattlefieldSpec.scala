package de.htwg.se.battleship.model

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class BattlefieldSpec extends AnyWordSpec with Matchers  {
  //TODO Test anpassen!
  "The Battlefield" when {
    //case class test for 100% Coverage
    "initialized" should {
      "have a  parameter" in {
        val caseClassPlayground = new Battlefield(Matrix(Vector(Vector(Cell(1)))))
        Battlefield.unapply(caseClassPlayground).get should be(Matrix(Vector(Vector(Cell(1)))))
      }
    }
  }
  /*
    "not set to any value " should {
      val emptyPBattlefield = Battlefield(0)
      "have value 0" in {
        emptyBattlefield.width should be(0)
        emptyBattlefield.hight should be(0)
      }
      "not be set" in {
        emptyBattlefield.widthAndHeightIsSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyBattlefield = Battlefield(1,1)
      "return that value" in {
        nonEmptyBattlefield.width should be(1)
        nonEmptyBattlefield.hight should be(1)
      }
      "be set" in {
        nonEmptyBattlefield.widthAndHeightIsSet should be(true)
      }
    }
    "BattlefieldString function" should {
      val nonEmptyBattlefieldString = Battlefield(0,0).playgroundString()
      "retrun a non empty String" in {
        nonEmptyBattlefieldString should not be empty
      }
    }
  }
*/
}
