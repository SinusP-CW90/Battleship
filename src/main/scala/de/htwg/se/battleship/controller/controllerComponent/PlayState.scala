package de.htwg.se.battleship.controller.controllerComponent

import de.htwg.se.battleship.util.State

case class PlayState(controller: ControllerInterface) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    if (input.contains("play")) {
      println("play State")
    } else {
      println("else im play State")
    }
  }
}
