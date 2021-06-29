package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec._


class CellSpec extends AnyWordSpec with Matchers {
  "A Battlefield Cell" when {
    //case class test for 100% Coverage
    "initialized" should {
      "a int parameter" in {
        val caseClassCell = Cell(0)
        Cell.unapply(caseClassCell).get should be(0)
      }
    }
    "not set to any value " should {
      val emptyCell = Cell(0)
      "have value 0" in {
        emptyCell.value should be(0)
      }
      "not be set" in {
        emptyCell.isSet should be(false)
      }
    }
    "set to a value" should {
      val cellWithValue = Cell(1)
      "return that value" in {
        cellWithValue.value should be(1)
      }
      "be set" in {
        cellWithValue.isSet should be(true)
      }
    }
    "call toString" should {
      val cellWithValue0 = Cell(0)
      val cellWithValue1 = Cell(1)
      val cellWithValue2 = Cell(2)
      val cellWithValue3 = Cell(3)
      val cellWithValue4 = Cell(4)
      "return another value in a String" in {
        cellWithValue0.toString should be(".")
        cellWithValue1.toString should be("S")
        cellWithValue2.toString should be("X")
        cellWithValue3.toString should be("o")
        cellWithValue4.toString should be("?")
      }
    }
  }
}
