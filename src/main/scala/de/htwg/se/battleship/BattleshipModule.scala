package de.htwg.se.battleship

import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.battleship.controller.controllerComponent._
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.Battlefield

class BattleshipModule extends AbstractModule with ScalaModule{

  val defaultSize:Int = 4

  override def configure():Unit = {
    //bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)

    bind[ControllerInterface].to[controllerBaseImpl.Controller]


/*
    bind[BattlefieldInterface].annotatedWithName("tiny").toInstance(new Battlefield(2))
    bind[BattlefieldInterface].annotatedWithName("small").toInstance(new Battlefield(4))
    bind[BattlefieldInterface].annotatedWithName("normal").toInstance(new Battlefield(9))

 */
  }


}
