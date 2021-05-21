package de.htwg.se.battleship.controller

import de.htwg.se.battleship.util.State

case class PlayState(controller: Controller) extends State[GameState] {
  override def handle(input: String, currentState: GameState): Unit = {
    if (input.contains("play")) {
      println("play State")
    } else {
      println("else im play State")
    }
  }
}