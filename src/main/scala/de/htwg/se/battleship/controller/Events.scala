package de.htwg.se.battleship.controller

import scala.swing.event.Event

class CellChanged extends Event
case class GridSizeChanged(newSize: Int) extends Event