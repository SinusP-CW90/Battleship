package de.htwg.se.battleship

import de.htwg.se.battleship.model._

import scala.io.StdIn.readLine

object Battleship {
  def main(args: Array[String]): Unit ={
    println("Welcome to Battleship");

    println("Please set a Name for the Player 1!");
    val player1 = Player(readLine())
    println("Please set a Name for the Player 2!");
    val player2 = Player(readLine())
    println("Hello, " + player1.name +" and " +player2.name)
    val emptyPg = new Battlefield(6)
    var pgP1L = emptyPg
    var pgP2R = emptyPg
    println("This is your Battlefield:");
    println(emptyPg.playgroundString(pgP1L,pgP2R,"p1"))

  }
}
