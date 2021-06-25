package de.htwg.se.battleship.model.fileIOComponent

import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface

trait FileIOInterface {

  def load(fileName:String): BattlefieldInterface
  def save(fileName:String, battlefield: BattlefieldInterface): Unit

}
