package de.htwg.se.battleship

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.battleship.controller.controllerComponent._
import de.htwg.se.battleship.model.battlefieldComponent.BattlefieldInterface
import de.htwg.se.battleship.model.battlefieldComponent.battlefieldBaseImpl.{Battlefield, BattlefieldCreateRandomStrategy}
import de.htwg.se.battleship.model.fileIOComponent._
import de.htwg.se.battleship.util.BattlefieldCreateStrategyTemplate
import net.codingwell.scalaguice.ScalaModule

class BattleshipModule extends AbstractModule{

  val defaultSize:Int = 2

  override def configure():Unit = {
//scala3 change
    bind(classOf[ControllerInterface]).to(classOf[controllerBaseImpl.Controller])
    //bind[Command].to[SetCommand]
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("DefaultSize")).toInstance(new Battlefield(defaultSize))

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)

    bind(classOf[BattlefieldCreateStrategyTemplate]).annotatedWith(Names.named("RandomStrategy")).toInstance(new BattlefieldCreateRandomStrategy)

    bind(classOf[FileIOInterface]).to(classOf[fileIOJsonImpl.FileIOJson])
    //bind[FileIOInterface].to[fileIOXmlImpl.FileIOXml]

    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-2x2")).toInstance(new Battlefield(2))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-2x2")).toInstance(new Battlefield(2))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-3x3")).toInstance(new Battlefield(3))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-3x3")).toInstance(new Battlefield(3))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-4x4")).toInstance(new Battlefield(4))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-4x4")).toInstance(new Battlefield(4))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-5x5")).toInstance(new Battlefield(5))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-5x5")).toInstance(new Battlefield(5))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-6x6")).toInstance(new Battlefield(6))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-6x6")).toInstance(new Battlefield(6))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p1-9x9")).toInstance(new Battlefield(9))
    bind(classOf[BattlefieldInterface]).annotatedWith(Names.named("p2-9x9")).toInstance(new Battlefield(9))


  }
}
