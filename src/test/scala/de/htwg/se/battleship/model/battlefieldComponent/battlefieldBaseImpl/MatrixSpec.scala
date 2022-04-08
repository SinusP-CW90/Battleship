package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec._

class MatrixSpec extends AnyWordSpec with Matchers {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of Cells. " +
    "A Matrix" when {
    //case class test for 100% Coverage
    "initialized" should {
      "have a 2d Vector parameter" in {
        val caseClassMatrix = Matrix(Vector(Vector(Cell(0))))
        //Matrix.unapply(caseClassMatrix).get should be(Vector(Vector(Cell(0))))
      }
    }
    "empty " should {
      "be created by using a dimention and a sample cell" in {
        val matrix = new Matrix[Cell](2, Cell(0))
        matrix.size should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(Cell(0))))
        testMatrix.size should be(1)
      }
    }
    "filled" should {
      val matrix = new Matrix[Cell](2, Cell(5))
      "give access to its cells" in {
        matrix.cell(0, 0) should be(Cell(5))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, Cell(4))
        matrix.cell(0, 0) should be(Cell(5))
        returnedMatrix.cell(0, 0) should be(Cell(4))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(Cell(3))
        returnedMatrix.cell(0,0) should be(Cell(3))
      }
    }
  }
}
