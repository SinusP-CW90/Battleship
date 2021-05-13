package de.htwg.se.battleship

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayInputStream

class BattleshipSpec extends AnyWordSpec with Matchers {
  "The battleship main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
     Battleship.main(Array[String]("s","test"))

    }

    "started" should {
      "run  without exceptions" in {
        val in = new ByteArrayInputStream("q".getBytes)
        Console.withIn(in) {
          noException should be thrownBy Battleship.main(Array[String](""))
        }
      }
    }
  }
}
