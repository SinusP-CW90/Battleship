package de.htwg.se.battleship.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import de.htwg.se.battleship.model.battlefieldComponent.{BattlefieldInterface, CellInterface}
import de.htwg.se.battleship.model.fileIOComponent.FileIOInterface
import play.api.libs.json.{JsNumber, JsObject, JsValue, Json, Writes}

import scala.io.Source

class FileIOJson extends FileIOInterface {

  override def load: BattlefieldInterface = {
    var battlefield: BattlefieldInterface = null
    val source: String = Source.fromFile("battlefield.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    //source.close()
    val size = (json \ "battlefield" \ "size").get.toString.toInt

    battlefield = new Battlefield(size)

    /*
    val injector = Guice.createInjector(new BattleshipModule)
    size match {
      case 1 => grid = injector.instance[BattlefieldInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[BattlefieldInterface](Names.named("small"))
      case 9 => grid = injector.instance[BattlefieldInterface](Names.named("normal"))
      case _ =>
    }

     */
    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[Int]
      battlefield = battlefield.set(row, col, value)
    }
    battlefield
  }

  override def save(battlefield: BattlefieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("battlefield.json"))
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
