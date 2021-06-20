package de.htwg.se.battleship.model.fileIOComponent

import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface

trait FileIOInterface {

  def load: BattlefieldInterface
  def save(battlefield: BattlefieldInterface): Unit

}
