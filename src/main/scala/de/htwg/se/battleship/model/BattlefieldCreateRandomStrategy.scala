package de.htwg.se.battleship.model

import de.htwg.se.battleship.util.BattlefieldCreateStrategyTemplate

import scala.util.Random

class BattlefieldCreateRandomStrategy extends BattlefieldCreateStrategyTemplate{
  def fill(_battlefield:Battlefield): Battlefield = {
    //val num = Math.sqrt(_battlefield.size).toInt
    var battlefield = new Battlefield(_battlefield.size)
    for {index <- 1 to _battlefield.size} {
      battlefield = setRandomCell(battlefield)
    }
    battlefield
  }

  private def setRandomCell(battlefield:Battlefield): Battlefield = {
      var row = Random.nextInt(battlefield.size)
      var column = Random.nextInt(battlefield.size)
    while (battlefield.cell(row,column).value != 0){
      row = Random.nextInt(battlefield.size)
      column = Random.nextInt(battlefield.size)
    }
      val rBattlefield = battlefield.set(row,column,1)

    rBattlefield
  }
}
