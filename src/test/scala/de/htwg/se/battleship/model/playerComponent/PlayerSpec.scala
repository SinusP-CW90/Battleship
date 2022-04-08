package de.htwg.se.battleship.model.playerComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec._

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Battleship Player" when {
    //case class test for 100% Coverage
    "initialized" should {
      "have a string parameter" in {
        val caseClassPlayer = Player("Name")
       // Player.unapply(caseClassPlayer).get should be("Name")
      }
    }
    "new" should {
      val player = Player("Your Name")
      "have a name"  in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
      "not set to any value " should {
        val noPlayerName = Player("")
        "not be set" in {
          noPlayerName.isSet should be(false)
        }
      }
    }
    "initialized a Vector of 2 Player" should {
      val noPlayerName = Player().setDefaultPlayerNames()
      "have following  Vector" in {
        noPlayerName.toString() should be("Vector(Player 1, Player 2)")
      }
    }
    "initialized a Vector of 2 Player" should {
      val noPlayerName = Player().playerNamesToString(Player().setDefaultPlayerNames())
      "have following Vector String" in {
        noPlayerName should be("Hello Player 1 (left Side) and Player 2 (right side)")
      }
    }

  }
}
