package de.htwg.se.battleship.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface

import scala.xml.{Elem, PrettyPrinter}

class FileIOXml extends FileIOInterface{

  override def load: BattlefieldInterface = {
    var battlefield: BattlefieldInterface = null
    val file = scala.xml.XML.loadFile("battlefield.xml")
    val sizeAttr = file \\ "battlefield" \ "@size"
    val size = sizeAttr.text.toInt
    /*
    val injector = Guice.createInjector(new BattleshipModule)
    size match {
      case 1 => grid = injector.instance[BattlefieldInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[BattlefieldInterface](Names.named("small"))
      case 9 => grid = injector.instance[BattlefieldInterface](Names.named("normal"))
      case _ =>
    }

     */
    val cellNodes = file \\ "cell"
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: Int = cell.text.trim.toInt
      battlefield = battlefield.set(row, col, value)

    }
    battlefield
  }

  def save(battlefield: BattlefieldInterface): Unit = saveString(battlefield)

  def saveXML(battlefield: BattlefieldInterface): Unit = {
    scala.xml.XML.save("battlefield.xml", battlefieldToXml(battlefield))
  }

  def saveString(battlefield: BattlefieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("battlefield.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(battlefieldToXml(battlefield))
    pw.write(xml)
    pw.close()
  }
  def battlefieldToXml(battlefield: BattlefieldInterface): Elem = {
    <grid size={ battlefield.size.toString }>
      {
      for {
        row <- 0 until battlefield.size
        col <- 0 until battlefield.size
      } yield cellToXml(battlefield, row, col)
      }
    </grid>
  }

  def cellToXml(battlefield: BattlefieldInterface, row: Int, col: Int): Elem = {
    <cell row={ row.toString } col={ col.toString }>
      { battlefield.cell(row, col).value }
    </cell>
  }

}
