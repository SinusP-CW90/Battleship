package de.htwg.se.battleship.controller.controllerComponent.battleshipGameStates

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship.util.State

case class SetShipsState(controller: ControllerInterface) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    input match {
      case "setShips" =>      controller.currentGameState="setShips"
        println(Console.RED +"State: "+ Console.RESET+controller.currentGameState)
        println("current Player = " + controller.playerSite)

        if(controller.playerSite=="l") {
          println("Player1 Ships left to set:" + (controller.shipsToSetP1-1))
          controller.shipsToSetP1 = controller.shipsToSetP1 - 1
          println(controller.shipsToSetP1)

          if (controller.shipsToSetP1 == 0) {
            println(Console.RED + "Now it is Player 2's turn"+Console.RESET)
            controller.playerSite = "r"
          }
        }
        if(controller.playerSite=="r") {
          println("Player2 Ships left to set:" + controller.shipsToSetP2)
          controller.shipsToSetP2 = controller.shipsToSetP2 - 1
          println(controller.shipsToSetP2)
          if (controller.shipsToSetP2 == 0) {
            currentState.nextState(ShootShipsState(controller))
          }
        }
      case "start" => currentState.nextState(StartState(controller))
      case "shoot"|"random" => currentState.nextState(ShootShipsState(controller))
      case _ => println("else im setShips State. Value: " + input)
    }
  }
}

