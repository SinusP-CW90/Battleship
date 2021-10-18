package de.htwg.se.battleship

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.battleship.controller.controllerComponent._
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, BattlefieldCreateRandomStrategy}
import de.htwg.se.battleship.model.fileIOComponent._
import de.htwg.se.battleship.util.BattlefieldCreateStrategyTemplate
import net.codingwell.scalaguice.ScalaModule

class BattleshipModule extends AbstractModule with ScalaModule{

  val defaultSize:Int = 4

  override def configure():Unit = {

    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    //bind[Command].to[SetCommand]
    bind[BattlefieldInterface].annotatedWithName("DefaultSize").toInstance(new Battlefield(defaultSize))

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)

    bind[BattlefieldCreateStrategyTemplate].annotatedWithName("RandomStrategy").toInstance(new BattlefieldCreateRandomStrategy)

    bind[FileIOInterface].to[fileIOJsonImpl.FileIOJson]
    //bind[FileIOInterface].to[fileIOXmlImpl.FileIOXml]

    bind[BattlefieldInterface].annotatedWithName("p1-2x2").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("p2-2x2").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("p1-3x3").toInstance(new Battlefield(3))
    bind[BattlefieldInterface].annotatedWithName("p2-3x3").toInstance(new Battlefield(3))
    bind[BattlefieldInterface].annotatedWithName("p1-4x4").toInstance(new Battlefield(4))
    bind[BattlefieldInterface].annotatedWithName("p2-4x4").toInstance(new Battlefield(4))
    bind[BattlefieldInterface].annotatedWithName("p1-5x5").toInstance(new Battlefield(5))
    bind[BattlefieldInterface].annotatedWithName("p2-5x5").toInstance(new Battlefield(5))
    bind[BattlefieldInterface].annotatedWithName("p1-6x6").toInstance(new Battlefield(6))
    bind[BattlefieldInterface].annotatedWithName("p2-6x6").toInstance(new Battlefield(6))
    bind[BattlefieldInterface].annotatedWithName("p1-9x9").toInstance(new Battlefield(9))
    bind[BattlefieldInterface].annotatedWithName("p2-9x9").toInstance(new Battlefield(9))


  }
}
