package de.htwg.se.battleship

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.battleship.controller.controllerComponent._
import de.htwg.se.battleship.controller.controllerComponent.controllerBaseImpl.SetCommand
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, BattlefieldCreateRandomStrategy}
import de.htwg.se.battleship.model.fileIOComponent._
import de.htwg.se.battleship.util.{BattlefieldCreateStrategyTemplate, Command}
import net.codingwell.scalaguice.ScalaModule

class BattleshipModule extends AbstractModule with ScalaModule{

  val defaultSize:Int = 4

  override def configure():Unit = {

    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    //bind[Command].to[SetCommand]
    bind[BattlefieldInterface].annotatedWithName("DefaultSize").toInstance(new Battlefield(defaultSize))

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)

    bind[BattlefieldCreateStrategyTemplate].annotatedWithName("RandomStrategy").toInstance(new BattlefieldCreateRandomStrategy)

    bind[BattlefieldInterface].annotatedWithName("p1-tiny").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("p2-tiny").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("p1-small").toInstance(new Battlefield(4))
    bind[BattlefieldInterface].annotatedWithName("p2-small").toInstance(new Battlefield(4))
    bind[BattlefieldInterface].annotatedWithName("p1-normal").toInstance(new Battlefield(6))
    bind[BattlefieldInterface].annotatedWithName("p2-normal").toInstance(new Battlefield(6))
    bind[BattlefieldInterface].annotatedWithName("p1-big").toInstance(new Battlefield(9))
    bind[BattlefieldInterface].annotatedWithName("p2-big").toInstance(new Battlefield(9))

    //bind[FileIOInterface].to[fileIOJsonImpl.FileIOJson]
    bind[FileIOInterface].to[fileIOXmlImpl.FileIOXml]
  }
}
