package de.htwg.se.battleship.util

import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield


trait BattlefieldCreateStrategyTemplate {

  def createNewGrid(size: Int): BattlefieldInterface = {
    var battlefield:BattlefieldInterface = new Battlefield(size)
    battlefield = prepare(battlefield)
    battlefield = fill(battlefield)
    battlefield
  }

  def prepare(battlefield: BattlefieldInterface): BattlefieldInterface = {
    // by default do nothing
    battlefield
  }

  def fill(battlefield: BattlefieldInterface): BattlefieldInterface // abstract


}
