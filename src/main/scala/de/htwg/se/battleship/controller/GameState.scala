package de.htwg.se.battleship.controller

import de.htwg.se.battleship.util.State

case class GameState(controller: Controller) {
  var state: State[GameState] = StartState(controller)

  def handle(string: String): Unit = {
    state.handle(string, this)
  }

  def nextState(state: State[GameState]): Unit = {
    this.state = state
  }
}