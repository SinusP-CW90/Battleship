package de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.util.State

case class WinState(controller: ControllerInterface) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    input match {
      case "win" => controller.playerSite match {
                    case "l" => println(Console.RED + "BOOOOOOOOOOM!!!! Player 1 on the left Side wins the game."+ Console.RESET)
                    case "r" => println(Console.RED + "BOOOOOOOOOOM!!!! Player 1 on the left Side wins the game."+ Console.RESET)
                    //case _ => println(Console.RED + "Nobody wins the game :-( "+ Console.RESET)
      }
                    currentState.nextState(StartState(controller))

      //case _ => println("else im win State. Value: "+input)
        //currentState.nextState(StartState(controller))

    }
  }
}