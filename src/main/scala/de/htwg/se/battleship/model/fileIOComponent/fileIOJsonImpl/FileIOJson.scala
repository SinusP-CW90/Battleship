package de.htwg.se.battleship.model.fileIOComponent.fileIOJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.battleship.BattleshipModule
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import play.api.libs.json._

import scala.io.Source

class FileIOJson extends FileIOInterface {

  override def load(fileName:String): Option[BattlefieldInterface] = {
    var battlefieldOption: Option[BattlefieldInterface] = None
    val source: String = Source.fromFile(fileName+".json").getLines.mkString
    val json: JsValue = Json.parse(source)
    //source.close()
    val size = (json \ "battlefield" \ "size").get.toString.toInt

    //battlefieldOption = Some(new Battlefield(size))

    val injector = Guice.createInjector(new BattleshipModule)
    size match {
      case 2 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-2x2")))
      case 3 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-3x3")))
      case 4 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-4x4")))
      case 5 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-5x5")))
      case 6 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-6x6")))
      case 9 => battlefieldOption = Some(injector.instance[BattlefieldInterface](Names.named("p1-9x9")))
      case _ =>
    }

    battlefieldOption match {
      case Some(battlefield) =>
        var _battlefield = battlefield
        for (index <- 0 until size * size) {
          val row = (json \\ "row") (index).as[Int]
          val col = (json \\ "col") (index).as[Int]
          val cell = (json \\ "cell") (index)
          val value = (cell \ "value").as[Int]
          _battlefield = _battlefield.set(row, col, value)
        }
        battlefieldOption=Some(_battlefield)
      case None =>
    }
    battlefieldOption
  }

  override def save(fileName:String,battlefield: BattlefieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(fileName+".json"))
    pw.write(Json.prettyPrint(battlefieldToJson(battlefield)))
    pw.close()
  }

  implicit val cellWrites: Writes[CellInterface] = (cell: CellInterface) => Json.obj(
    "value" -> cell.value
  )

  def battlefieldToJson(battlefield: BattlefieldInterface): JsObject = {
    Json.obj(
      "battlefield" -> Json.obj(
        "size" -> JsNumber(battlefield.size),
        "cells" -> Json.toJson(
          for {
            row <- 0 until battlefield.size
            col <- 0 until battlefield.size
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(battlefield.cell(row, col))
            )
          }
        )
      )
    )
  }

}
