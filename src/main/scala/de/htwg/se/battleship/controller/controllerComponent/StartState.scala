package de.htwg.se.battleship.controller.controllerComponent

import de.htwg.se.battleship.util.State

case class StartState(controller: ControllerInterface) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    if (input.contains("start")) {
      println("start State")
      currentState.nextState(PlayState(controller))
    } else {
      println("else im start State")
    }
  }
}