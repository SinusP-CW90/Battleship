package de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl

import scala.swing.event.Event

class CellChanged extends Event
case class BattlefieldSizeChanged(newSize: Int) extends Event