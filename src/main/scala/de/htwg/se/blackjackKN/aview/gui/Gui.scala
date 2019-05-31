package de.htwg.se.blackjackKN.aview.gui

import scalafx.Includes._
import de.htwg.se.blackjackKN.controller.Controller
import de.htwg.se.blackjackKN.util.Observer
import javafx.geometry._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.{Scene, SubScene}
import scalafx.scene.layout.{BorderPane, FlowPane, HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.{Text, TextAlignment}
import scalafx.scene.control._

class Gui(controller: Controller) extends JFXApp with Observer{
  controller.add(this)
  stage = new PrimaryStage {
    onCloseRequest = handle { exit() }
    title = "BlackjackKN"
    width = 1200
    height = 800
    scene = new Scene {
      root = new BorderPane {
        top = new HBox {
          children = new Text {
            text = "BLACKJACK"
            style = "-fx-font-size: 36pt"
            alignment = Pos.CENTER
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          }
        }
        center = new VBox {

          alignment = Pos.CENTER
          spacing = 20
          style = "-fx-font-size: 12pt"
          children = Seq(
            new Text {
              textAlignment = TextAlignment.Center
              text = "Hello " + controller.player.name + "!\nYour balance is " + controller.player.balance + "$"
            },
            new Button {
              text = "Start New Round!"
              onAction = handle {
                startNewRound()
              }
            },
            new Button {
              text = "Exit Game"
              onAction = handle { exit() }
            }
          )
        }
        bottom = new HBox{
          alignment = Pos.CENTER
          spacing = 20
          children = Seq(
            new Text {
              text = "Developed by Jana Siebenhaller and Benjamin Jasper"
              padding = Insets(40,0,20,0)
            }
          )
        }
      }
    }
  }
  def startNewRound() : Unit = {
    val dialog = new TextInputDialog(defaultValue = (controller.player.balance / 8).toInt.toString) {
      initOwner(stage)
      title = "Bet amount"
      headerText = "What amount of your " + controller.player.balance + "$ would you like to bet, " + controller.player.name +"?"
      contentText = "Enter the amount here:"
    }

    val result = dialog.showAndWait()

    result match {
      case Some(value) =>
        try {
          val int = value.toDouble
          if (controller.setBet(int.toInt)) {
            controller.startNewRound()
            setPlayingScene()
          } else {
            new Alert(AlertType.Error) {
              initOwner(stage)
              title = "Bet failed"
              headerText = "Not enough money"
              contentText = "You don't have enough money mate"
            }.showAndWait()
          }
        }
      case None =>
    }


  }

  def exit() : Unit = {
    println("Exiting game...")
    System.exit(0)
  }

  def setPlayingScene() : Unit = {
    stage.scene = new Scene {
      fill = Black
      content = new FlowPane {
        children = Seq(
          new Text {
            text = "NEW ROUND"
            fill = White
          }
        )
      }
    }
  }

  override def update: Boolean = true
}
