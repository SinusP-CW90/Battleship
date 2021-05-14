package de.htwg.se.battleship.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val START, HELP, IDLE, WIN = Value

  val map = Map[GameStatus, String](
    START-> "Welcome to Battleships",
    //Liste aller input befehle
    HELP -> "TODO",
    IDLE -> "There are still ships on the battlefield",
    WIN ->"You WIN!!!! \n You destroyed all of your opponent's ships!")


  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }
}
