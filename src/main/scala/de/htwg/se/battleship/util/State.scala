package de.htwg.se.battleship.util

trait State[T] {
  def handle(string: String, n: T):Unit
}
