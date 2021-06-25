package de.htwg.se.battleship.model.fileIOComponent.fileIOXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.battleship.BattleshipModule
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

import scala.xml.{Elem, PrettyPrinter}

class FileIOXml extends FileIOInterface{

  def load(fileName:String): Option[BattlefieldInterface] = {

    var battlefieldOption: Option[BattlefieldInterface] = None
    val file = scala.xml.XML.loadFile(fileName+".xml")
    //    val file = scala.xml.XML.loadFile("battlefield.xml")
    val sizeAttr = file \\ "battlefield" \ "@size"
    val size = sizeAttr.text.toInt

    battlefieldOption = Some(new Battlefield(size))
    /*
    val injector = Guice.createInjector(new BattleshipModule)
    size match {
      case 2 => battlefield = injector.instance[BattlefieldInterface](Names.named("p1-tiny"))
        //pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-tiny"))
      case 4 => battlefield = injector.instance[BattlefieldInterface](Names.named("p1-small"))
        //pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-small"))
      case 6 => battlefield = injector.instance[BattlefieldInterface](Names.named("p1-normal"))
        //pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-normal"))
      case 9 => battlefield = injector.instance[BattlefieldInterface](Names.named("p1-big"))
        //pgP2R = injector.instance[BattlefieldInterface](Names.named("p2-big"))
      case _ =>
    }*/

    val cellNodes = file \\ "cell"
    battlefieldOption match {
      case Some(battlefield)=> {
        var _battlefield = battlefield
        for (cell <- cellNodes) {
          val row: Int = (cell \ "@row").text.toInt
          val col: Int = (cell \ "@col").text.toInt
          val value: Int = cell.text.trim.toInt
          _battlefield = _battlefield.set(row, col, value)
        }
        battlefieldOption = Some(_battlefield)
      }
      case None =>
    }
    battlefieldOption
  }

  def save(fileName:String, battlefield: BattlefieldInterface): Unit = saveString(fileName:String, battlefield)

  def saveXML(fileName:String, battlefield: BattlefieldInterface): Unit = {
    scala.xml.XML.save(fileName+".xml", battlefieldToXml(battlefield))
    //    scala.xml.XML.save("battlefield.xml", battlefieldToXml(battlefield))
  }

  def saveString(fileName:String, battlefield: BattlefieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(fileName+".xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(battlefieldToXml(battlefield))
    pw.write(xml)
    pw.close()
  }
  def battlefieldToXml(battlefield: BattlefieldInterface): Elem = {
    <battlefield size={ battlefield.size.toString }>
      {
      for {
        row <- 0 until battlefield.size
        col <- 0 until battlefield.size
      } yield cellToXml(battlefield, row, col)
      }
    </battlefield>
  }

  def cellToXml(battlefield: BattlefieldInterface, row: Int, col: Int): Elem = {
    <cell row={ row.toString } col={ col.toString }>
      { battlefield.cell(row, col).value }
    </cell>
  }

}
