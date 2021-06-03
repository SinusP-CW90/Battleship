package de.htwg.se.battleship.aview.gui

import scala.swing._
import scala.swing.event._
import de.htwg.se.battleship.controller.Controller
import de.htwg.se.battleship.controller.CellChanged

import scala.swing.Swing.LineBorder

class CellPanel(row: Int, column: Int, controller: Controller) extends FlowPanel {

  val cellColor = new Color(0, 0, 255)

  //def myCell = controller.cell(row, column)

  //def cellText(row: Int, col: Int) = if (controller.isSet(row, column)) "x" + controller.cell(row, column).value.toString else "o"
/*
  val label =
    new Label {
      text = cellText(row, column)
      font = new Font("Verdana", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(100, 51)
    //background = if (controller.isGiven(row, column)) givenCellColor else cellColor
    //border = Swing.BeveledBorder(Swing.Raised)
    border = LineBorder(java.awt.Color.RED, 5)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        label.text = cellText(row, column)
        repaint
      }
        /*
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.showCandidates(row, column)
        repaint
      }
         */
    }
  }
*/
  val candidatelist = (1 to 1).map {
    (value =>
      new Label {
        text = "x" //if (controller.available(row, column).contains(value)) value.toString else " "
        preferredSize = new Dimension(50, 50)
        font = new Font("Verdana", 1, 9)
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

  /*
  def redraw = {
    contents.clear()
    if (!controller.isSet(row, column)) {
      setBackground(candidates)
      contents += candidates
    } else {
      //label.text = cellText(row, column)
      //setBackground(cell)
      //contents += cell
    }
    repaint
  }
*/
  def setBackground(p: Panel) = p.background = cellColor

}