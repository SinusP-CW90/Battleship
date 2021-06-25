package de.htwg.se.battleship.model.playerComponent

trait PlayerInterface {
  def toString: String

  def isSet: Boolean

  def setDefaultPlayerNames(): Vector[Player]

  def playerNamesToString(names: Vector[Player]): String

}
