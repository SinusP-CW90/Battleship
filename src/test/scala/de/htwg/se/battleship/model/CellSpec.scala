package de.htwg.se.battleship.model

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers


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
  }
}
