package de.htwg.se.battleship.controller

object GameState {
  var state = onState
  /*
  def handle(e: Event) = {
    e match {
      case on: OnEvent => state = onState
      case off: OffEvent => state = offState
    }
    state
  }
*/
  def onState = println("I am on")
  def offState = println("I am off")


}

