package de.htwg.se.battleship.aview.gui

import de.htwg.se.battleship.controller.controllerComponent.ControllerInterface

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.GridSizeChanged
import de.htwg.se.battleship.util.Observer

import java.awt.Image
import javax.swing.{BorderFactory, ImageIcon}

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: ControllerInterface) extends Frame with Observer{

  listenTo(controller)

  title = "Battleship"
  //minimumSize = new Dimension(400, 400)
  var cells: Array[Array[CellPanel]] = Array.ofDim[CellPanel](controller.battlefieldSize, controller.battlefieldSize)

  def gridPanel: GridPanel = new GridPanel(controller.battlefieldSize, controller.blockSize) {
    border = LineBorder(java.awt.Color.BLACK, 5)
    for {
      innerRow <- 0 until controller.battlefieldSize
      innerColumn <- 0 until controller.battlefieldSize
    } {
      val x = innerRow
      val y = innerColumn
      //val cellPanel = new CellPanel(x, y, controller)
      val cellPanel = new CellPanel("l",x, y, controller)
      cells(x)(y) = cellPanel
      contents += cellPanel
      listenTo(cellPanel)
    }
  }

  def gridPanelTest: GridPanel = new GridPanel(controller.battlefieldSize, controller.blockSize) {
    border = LineBorder(java.awt.Color.BLACK, 5)
    for {
      innerRow <- 0 until controller.battlefieldSize
      innerColumn <- 0 until controller.battlefieldSize
    } {
      val x = innerRow
      val y = innerColumn
      val cellPanel = new CellPanel("r",x, y, controller)
      cells(x)(y) = cellPanel
      contents += cellPanel
      listenTo(cellPanel)
    }
  }

  def labelABC: GridPanel = new GridPanel(controller.battlefieldSize, 1) {
    for (i <- Range(0, controller.battlefieldSize, +1)) {
      contents += new Label {
        text = ("A"(0) + i).toChar.toString
        preferredSize = new Dimension(25, 50)
      }
    }
  }

  def label123: GridPanel = new GridPanel(1, controller.battlefieldSize) {
    border = BorderFactory.createEmptyBorder(0, 25, 0, 25)
    for (i <- Range(1, controller.battlefieldSize+1, +1)) {
      contents += new Label {
        horizontalAlignment = Alignment.Center
        text = i.toString
        preferredSize = new Dimension(50, 25)
      }
    }
  }

  def labelPanel: BoxPanel = new BoxPanel(orientation = Orientation.Horizontal) {
    contents += label123
    contents += label123
  }

  def statusPanel: BoxPanel = new BoxPanel(orientation = Orientation.Vertical) {
    contents += statusline
    contents += testline1
  }

  def titlePanel = new FlowPanel {
    contents += new Label()
    {
      icon = new ImageIcon(new ImageIcon("src/main/resources/battleship.jpg").getImage.getScaledInstance(700, 200, Image.SCALE_DEFAULT))
    }
  }

  def bPanel = new BoxPanel(Orientation.Vertical) {
        contents += new Label {
          // successfully displays my logo so no resource issues.
          icon = new ImageIcon(getClass.getResource("/ship1.png"))
        }
      }



  def NorthPanel: BoxPanel = new BoxPanel(orientation = Orientation.Vertical) {
    contents += titlePanel
    contents += labelPanel
  }



  val statusline = new TextField(controller.currentGameState, 20)
  val testline1 = new TextField("\n controller.battlefieldSize: "+controller.battlefieldSize+"\n controller.blockSize: "+controller.blockSize, 20)

  contents = new BorderPanel {
    add(new BoxPanel(orientation = Orientation.Horizontal) {
      contents += gridPanel
      contents += gridPanelTest
    }, BorderPanel.Position.Center)
    add(new BoxPanel(orientation = Orientation.Horizontal) {
      contents += NorthPanel
      //contents += bPanel
      //contents += label123
    }, BorderPanel.Position.North)
    add(new BoxPanel(orientation = Orientation.Vertical) {
      contents += labelPanel
      contents += statusPanel
    }, BorderPanel.Position.South)

    add(labelABC, BorderPanel.Position.West)
    add(labelABC, BorderPanel.Position.East)

  }




  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createEmptyBattlefield(controller.battlefieldSize) })
      contents += new MenuItem(Action("Save") { controller.save })
      contents += new MenuItem(Action("Load") { controller.load })
      contents += new MenuItem(Action("Random for P2") { controller.createRandomBattlefield("r",controller.battlefieldSize) })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo() })
      contents += new MenuItem(Action("Redo") { controller.redo() })
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
    case event: GridSizeChanged => resize()
    //case event: CellChanged     => redraw
  }

  def resize(): Unit = {
    cells = Array.ofDim[CellPanel](controller.battlefieldSize, controller.battlefieldSize)
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
        row <- 0 until controller.battlefieldSize
        column <- 0 until controller.battlefieldSize
      } cells(row)(column).redraw
      statusline.text = controller.statusText
      repaint
    }
  */
  override def update: Boolean = {true}
}