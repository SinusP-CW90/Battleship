package de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.util.State

case class StartState(controller: ControllerInterface) extends State[GameState] {

  override def handle(input: String, currentState: GameState): Unit = {
    input match {
      case "start" => controller.currentGameState = "start"
        println(Console.RED + "State: " + Console.RESET + controller.currentGameState)
        println(controller.setPlayerNames())
        println("Welcome to Battleship")
        println(Console.YELLOW + "INFO: " + Console.RESET + "Now you can set " + controller.battlefieldSize + " Ships on your Site\n-> For example, to set a ship, enter 'a1' or 'b2' etc.")
        currentState.nextState(SetShipsState(controller))
      case "setShips" => currentState.nextState(SetShipsState(controller))
      case _ => println("else im start State. Value: X" + input)
    }
  }
}
