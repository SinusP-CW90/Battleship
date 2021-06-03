package de.htwg.se.battleship.aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.battleship.controller._
import de.htwg.se.battleship.util.Observer
import javax.swing.BorderFactory

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: Controller) extends Frame with Observer{

  listenTo(controller)

  title = "Battleship"
  //minimumSize = new Dimension(400, 400)
  var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)

  def gridPanel = new GridPanel(controller.gridSize, controller.blockSize) {
        border = LineBorder(java.awt.Color.BLACK, 5)
        for {
          innerRow <- 0 until controller.gridSize
          innerColumn <- 0 until controller.gridSize
        } {
          val x = innerRow
          val y = innerColumn
          val cellPanel = new CellPanel(x, y, controller)
          cells(x)(y) = cellPanel
          contents += cellPanel
          listenTo(cellPanel)
        }
  }

  def labelABC: GridPanel = new GridPanel(controller.gridSize, 1) {
    for (i <- Range(0, controller.gridSize, +1)) {
      contents += new Label {
        text = ("A"(0) + i).toChar.toString
        preferredSize = new Dimension(25, 50)
      }
    }
  }

  def label123: GridPanel = new GridPanel(1, controller.gridSize) {
    border = BorderFactory.createEmptyBorder(0, 25, 0, 25)
    for (i <- Range(1, controller.gridSize+1, +1)) {
      contents += new Label {
        horizontalAlignment = Alignment.Center
        text = i.toString
        preferredSize = new Dimension(50, 25)
      }
    }
  }

  val statusline = new TextField(controller.statusText, 20)
  val testline1 = new TextField("\n controller.gridSize: "+controller.gridSize+"\n controller.blockSize: "+controller.blockSize, 20)

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
    add(label123, BorderPanel.Position.North)
    add(label123, BorderPanel.Position.South)
    add(labelABC, BorderPanel.Position.West)
    add(labelABC, BorderPanel.Position.East)
    add(statusline, BorderPanel.Position.South)
    add(testline1, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createEmptyPlayground(controller.gridSize) })
      contents += new MenuItem(Action("Random for P2") { controller.createRandomBattlefield("r",controller.gridSize) })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Size 1*1") { controller.resize(1) })
      contents += new MenuItem(Action("Size 2*2") { controller.resize(2) })
      contents += new MenuItem(Action("Size 3*3") { controller.resize(3) })
      contents += new MenuItem(Action("Size 4*4") { controller.resize(4) })
      contents += new MenuItem(Action("Size 5*5") { controller.resize(5) })
      contents += new MenuItem(Action("Size 6*6") { controller.resize(6) })
      contents += new MenuItem(Action("Size 9*9") { controller.resize(9) })

    }
  }

  visible = true
  //redraw

  reactions += {
    case event: GridSizeChanged => resize(event.newSize)
    //case event: CellChanged     => redraw
  }

  def resize(gridSize: Int) = {
    cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
      add(label123, BorderPanel.Position.North)
      add(label123, BorderPanel.Position.South)
      add(labelABC, BorderPanel.Position.West)
      add(labelABC, BorderPanel.Position.East)
      add(statusline, BorderPanel.Position.South)
      add(testline1, BorderPanel.Position.South)
    }
  }
/*
  def redraw = {
    for {
      row <- 0 until controller.gridSize
      column <- 0 until controller.gridSize
    } cells(row)(column).redraw
    statusline.text = controller.statusText
    repaint
  }
*/
  override def update(): Boolean = {true}
}
