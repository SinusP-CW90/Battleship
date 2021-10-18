package de.htwg.se.battleship.aview.gui

import javax.swing.ImageIcon
import java.awt.Image
import scala.swing._
import scala.swing.event._
import scala.swing.Swing.LineBorder
import de.htwg.se.battleship.controller.controllerComponent.{CellChanged, ControllerInterface}
import de.htwg.se.battleship.model.battlefieldComponent.CellInterface

import javax.swing.ImageIcon
import scala.io.Source

class CellPanel(player:String, row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {

  val cellColor = new Color(0, 0, 255)

  def myCell: CellInterface = controller.cell(row, column)

  //def cellText(row: Int, col: Int) = if (controller.isSet(row, column)) "x" + controller.cell(row, column).value.toString else "o"

  val celllist: IndexedSeq[Label] = (1 to 1).map {
    value =>
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
          case _: CellChanged =>
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
            border = LineBorder(java.awt.Color.BLUE, 5)
            repaint
          case MouseClicked(src, pt, mod, clicks, pops) =>
            //controller.setL(row, column, value)
            controller.setInGUI(player, row, column)
            border = LineBorder(java.awt.Color.GREEN, 5)
            //text = if (controller.available(row, column).contains(value)) value.toString else " "
                //text = controller.cell(row,column).toString
            controller.cell(row,column).value match{
              case 0 => text = controller.cell(row,column).toString
              case 1 => icon = new ImageIcon(new ImageIcon("src/main/resources/ship2.png").getImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT))
              case 2 => icon = new ImageIcon(new ImageIcon("src/main/resources/sinkingShip.png").getImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT))
              case 3 => icon = new ImageIcon(new ImageIcon("src/main/resources/miss.png").getImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT))
            }


            repaint
        }
      }
  }

  val candidates: GridPanel = new GridPanel(1, 1) {
    setBackground(this)
    border = LineBorder(java.awt.Color.BLACK, 5)
    contents ++= celllist
  }
  contents += candidates


  def setBackground(p: Panel): Unit = p.background = cellColor

}