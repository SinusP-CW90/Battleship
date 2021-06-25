package de.htwg.se.battleship.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface
import play.api.libs.json.{JsNumber, JsObject, JsValue, Json, Writes}

import scala.io.Source

class FileIOJson extends FileIOInterface {

  override def load(fileName:String): Option[BattlefieldInterface] = {
    var battlefieldOption: Option[BattlefieldInterface] = None
    val source: String = Source.fromFile(fileName+".json").getLines.mkString
    val json: JsValue = Json.parse(source)
    //source.close()
    val size = (json \ "battlefield" \ "size").get.toString.toInt

    battlefieldOption = Some(new Battlefield(size))

    /*
    val injector = Guice.createInjector(new BattleshipModule)
    size match {
      case 1 => grid = injector.instance[BattlefieldInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[BattlefieldInterface](Names.named("small"))
      case 9 => grid = injector.instance[BattlefieldInterface](Names.named("normal"))
      case _ =>
    }

     */
    battlefieldOption match {
      case Some(battlefield) => {
        var _battlefield = battlefield
        for (index <- 0 until size * size) {
          val row = (json \\ "row") (index).as[Int]
          val col = (json \\ "col") (index).as[Int]
          val cell = (json \\ "cell") (index)
          val value = (cell \ "value").as[Int]
          _battlefield = _battlefield.set(row, col, value)
        }
        battlefieldOption=Some(_battlefield)
      }
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
