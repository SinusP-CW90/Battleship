package de.htwg.se.battleship


import de.htwg.se.battleship.model._
import de.htwg.se.battleship.aview.Tui
import de.htwg.se.battleship.controller.Controller

import scala.io.StdIn.readLine

object Battleship {
  val controller = new Controller(new Battlefield(3),new Battlefield(3))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    println("Welcome to Battleship");

    println("Please set a Name for the Player 1!");
    val player1 = Player(readLine())
    println("Please set a Name for the Player 2!");
    val player2 = Player(readLine())
    var input: String = ""
    do {
      println("Hello, " + player1.name +" and " +player2.name)
      println("To start the Game please enter 'start', or change the size of the Battlefield")
      println("or change the size of the Battlefield\n" +
        "type 's' or 'small' for a 3x3 Battlefield\n"+
        "type 'm' or 'medium' for a 6x6 Battlefield\n"+
        "type 'l' or 'large' for a 9x9 Battlefield\n");

      println("after the start you can set your ships with `a1` or `b2` etc. ")
      input = readLine()
      tui.processInputLine(input)
    } while (input != "quit")
  }

}
