package de.htwg.se.battleship

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.battleship.controller.controllerComponent._
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield
import net.codingwell.scalaguice.ScalaModule

class BattleshipModule extends AbstractModule with ScalaModule{

  val defaultSize:Int = 4

  override def configure():Unit = {

    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[BattlefieldInterface].annotatedWithName("DefaultSize").toInstance(new Battlefield(defaultSize))
    //bind[BattlefieldInterface].annotatedWithName("p2").toInstance(new Battlefield(defaultSize))

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    //bind[BattlefieldInterface].to[Battlefield]
    //bind[BattlefieldInterface].annotatedWithName("Cells").toInstance(new Battlefield(2))
/*
    bind[BattlefieldInterface].annotatedWithName("p1").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("p2").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("normal").toInstance(new Battlefield(9))
*/
  }
}
