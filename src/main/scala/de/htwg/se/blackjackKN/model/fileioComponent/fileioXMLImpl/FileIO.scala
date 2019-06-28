package de.htwg.se.blackjackKN.model.fileioComponent.fileioXMLImpl

import com.google.inject.{Guice, Injector}
import com.google.inject.name.Names
import de.htwg.se.blackjackKN.BlackjackModule
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.blackjackKN.model.fileioComponent.FileIOInterface
import de.htwg.se.blackjackKN.model.personsComponent.PlayerInterface

import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}

class FileIO extends FileIOInterface{

  def load(playerName: String): PlayerInterface = {
    val file = scala.xml.XML.loadFile(playerName + ".xml")
    val balance : Double = (file \\ "player" \ "@balance").text.toDouble
    val injector = Guice.createInjector(new BlackjackModule)
    val player = injector.getInstance(classOf[PlayerInterface])
    player.setName(playerName)
    player.setBalance(balance)
    player
  }

  override def store(player: PlayerInterface): Boolean = {
    storeString(player)
    true
  }

  def storeString(player: PlayerInterface) = {
    import java.io._
    val pw = new PrintWriter(new File(player.getName + ".xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(playerToXml(player, player.balance))
    pw.write(xml)
    pw.close
  }
  def playerToXml(player: PlayerInterface, balance:Double) = {
    <player balance ={balance.toString}></player>
  }
}