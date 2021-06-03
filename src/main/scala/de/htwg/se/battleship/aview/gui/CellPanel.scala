package de.htwg.se.battleship.aview.gui

import scala.swing._
import scala.swing.event._
import de.htwg.se.battleship.controller.Controller
import de.htwg.se.battleship.controller.CellChanged

import scala.swing.Swing.LineBorder

class CellPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {

  val cellColor = new Color(0, 0, 255)

  def myCell = controller.cell(row, column)

  //def cellText(row: Int, col: Int) = if (controller.isSet(row, column)) "x" + controller.cell(row, column).value.toString else "o"

  val candidatelist = (1 to 1).map {
    (value =>
      new Label {
        //text = "x" //if (controller.available(row, column).contains(value)) value.toString else " "

        preferredSize = new Dimension(66, 66)
        font = new Font("Arial", 0, 22)
        foreground_=(java.awt.Color.BLACK): Unit
        background = cellColor
        //border = Swing.BeveledBorder(Swing.Raised)
        border = LineBorder(java.awt.Color.BLACK, 5)
        listenTo(mouse.clicks)
        listenTo(controller)
        reactions += {
          case e: CellChanged => {
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
            border = LineBorder(java.awt.Color.BLUE, 5)
            repaint
          }
          case MouseClicked(src, pt, mod, clicks, pops) => {
            controller.setL(row, column, value)
            border = LineBorder(java.awt.Color.GREEN, 5)
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
            text = row.toString +" " +column.toString
            repaint
          }
        }
      })
  }

  val candidates = new GridPanel(1, 1) {
    setBackground(this)
    border = LineBorder(java.awt.Color.BLACK, 5)
    contents ++= candidatelist
  }
  contents += candidates


  def setBackground(p: Panel) = p.background = cellColor

}