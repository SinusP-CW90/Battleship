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
      val emptyPlayground = Battlefield(0)
      "have value 0" in {
        emptyPlayground.width should be(0)
        emptyPlayground.hight should be(0)
      }
      "not be set" in {
        emptyPlayground.widthAndHeightIsSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyPlayground = Battlefield(1,1)
      "return that value" in {
        nonEmptyPlayground.width should be(1)
        nonEmptyPlayground.hight should be(1)
      }
      "be set" in {
        nonEmptyPlayground.widthAndHeightIsSet should be(true)
      }
    }
    "PlaygroundString function" should {
      val nonEmptyPlaygroundString = Battlefield(0,0).playgroundString()
      "retrun a non empty String" in {
        nonEmptyPlaygroundString should not be empty
      }
    }
  }
*/
}
