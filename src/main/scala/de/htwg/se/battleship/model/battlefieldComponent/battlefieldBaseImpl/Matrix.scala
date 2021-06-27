package de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl

/**The Matrix class serves as a generic template for the playground
 * @param rows is a 2D Vector of an Generic Datatype [T]. */
case class Matrix[T](rows: Vector[Vector[T]]) {
  //Funktion

  /**The Matrix class serves as a generic template for the playground
   * @param size is Square size of the 2D Vector.
   * @param filling fills the 2D vector with any data type*/
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })
  // "=> is syntactic sugar for creating instances of functions. Recall that every function in scala is an instance of a class.
  //For example, the type Int => String, is equivalent to the type Function1[Int,String]
  // i.e. a function that takes an argument of type Int and returns a String.

  /** the value size retrun the size of the 2d Vector rows*/
  val size: Int = rows.size

  /**the cell function addresses the exact cell in the Vector*/
  def cell(row: Int, col: Int): T = rows(row)(col)

  /**replaceCell creates a copy and updates the desired cell in the Matrix*/
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  /**the fill function fills the 2D vector / matrix from a square size with the desired data type (in this case with the cell class)*/
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
}

/*
/**Matrix Object to write and read the cells into Json */
object Matrix {
  import play.api.libs.json._
  implicit val matrixWrites: OWrites[Matrix[Cell]] = Json.writes[Matrix[Cell]]
  implicit val matrixReads: Reads[Matrix[Cell]] = Json.reads[Matrix[Cell]]
}

 */
