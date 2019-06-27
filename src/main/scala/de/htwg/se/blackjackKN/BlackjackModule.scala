package de.htwg.se.blackjackKN

import com.google.inject.AbstractModule
import de.htwg.se.blackjackKN.controller.controllerComponent._
import de.htwg.se.blackjackKN.model.personsComponent._
import net.codingwell.scalaguice.ScalaModule

class BlackjackModule extends AbstractModule with ScalaModule {

  def configure() = {
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[DealerInterface].toInstance(personsBaseImpl.Dealer())
    bind[PlayerInterface].toInstance(personsBaseImpl.Player())
  }

}