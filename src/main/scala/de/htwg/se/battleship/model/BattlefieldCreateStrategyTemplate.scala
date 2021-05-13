package de.htwg.se.battleship.model

trait BattlefieldCreateStrategyTemplate {
  def createNewGrid(size:Int): Battlefield = {
    var battlefield = new Battlefield(size)
    battlefield = prepare(battlefield)
    battlefield = fill(battlefield)
    battlefield
  }

  def prepare(battlefield: Battlefield):Battlefield = {
    // by default do nothing
    battlefield
  }

  def fill(battlefield: Battlefield) : Battlefield // abstract


}


