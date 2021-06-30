import de.htwg.se.battleship.Battleship.controller
val input ="111"
val inputA ="a1"

input.toList.filter(c => c != ' ').filter(_.isDigit).map(c => c.toString.toInt-1)

inputA.toList.filter(c => c != ' ').map(c => c.toString)

input.toList.filter(c => c != ' ').filter(_.isDigit).map(c => c.toString.toInt) match {
  case row :: column :: value :: Nil => controller.setL(row, column, value)
}

controller.setL(0, 0, 1)

inputA.toList.filter(c => c != ' ').filter(_.isDigit).map(c => c.toString) match {
  case row :: column :: Nil => controller.set(row, column)
}
