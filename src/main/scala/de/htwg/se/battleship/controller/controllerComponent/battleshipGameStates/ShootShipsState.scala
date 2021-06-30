package de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.util.State

case class ShootShipsState(controller: ControllerInterface) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    input match {
      case "shoot"|"setShips"|"random" => controller.currentGameState="shoot"
        println(Console.YELLOW +"INFO:"+ Console.RESET)
        println("State: "+controller.currentGameState)
        println("Current action: The Player "+controller.playerSite +" has to guess where one of the opponent's ships is")
      case "win" => currentState.nextState(WinState(controller))
      case _ =>       println("else im shoot State. Value: "+input)
    }
  }
}