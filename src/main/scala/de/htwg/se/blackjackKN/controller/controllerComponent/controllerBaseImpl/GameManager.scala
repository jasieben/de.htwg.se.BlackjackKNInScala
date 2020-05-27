package de.htwg.se.blackjackKN.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.blackjackKN.model.{Dealer, Ranks}
import de.htwg.se.blackjackKN.model.cardsComponent.CardInterface
import de.htwg.se.blackjackKN.model.cardsComponent.cardsBaseImpl.CardDeck

import scala.util.Random

case class GameManager(dealerHand : List[CardInterface], playerHands: List[List[CardInterface]], cardDeck: List[CardInterface]) {

  def generateDealerCards: GameManager = {
    val baseCardDeck : List[CardInterface] = CardDeck().cardDeck

    val playCards: List[CardInterface] = baseCardDeck ::: baseCardDeck ::: baseCardDeck ::: baseCardDeck ::: baseCardDeck

    copy(cardDeck = Random.shuffle(playCards))
  }

  def addCardToPlayerHand(playerIndex: Int,card: CardInterface): GameManager = {
    val playerHand : List[CardInterface] = playerHands(playerIndex) :+ card

    copy(playerHands = playerHands.updated(playerIndex, playerHand))
  }

  def replaceCardInHand(playerIndex : Int,handIndex: Int, newCard: CardInterface): GameManager = {
    val playerHand : List[CardInterface] = playerHands(playerIndex).updated(handIndex, newCard)
    copy(playerHands = playerHands.updated(playerIndex, playerHand))
  }

  def changeBalance(value: Int): GameManager = copy(balance = value)

  def clearHand(): GameManager = copy(hand = List[CardInterface]())

  def getCard(index: Int): CardInterface = hand(index)

  def getHandSize: Int = hand.size

  def getHandValue: Int = {
    var v: Int = 0
    for {
      i <- hand.indices
    } v += hand(i).value
    v
  }

  def getLastHandCard: CardInterface = hand.last

  def containsCardType(rank: Ranks.Value): Int = {
    for (i <- hand.indices) {
      if (hand(i).rank == rank) {
        return i
      }
    }
    -1
  }

  def cleardealerHand(): GameManager = {
    copy(dealerHand = List[CardInterface]())
  }

  def drawCard(): CardInterface = {
    cardDeck.head
  }

  def dropCard(): GameManager = {
    copy(cardDeck = cardDeck.drop(1))
  }

  def renewCardDeck(): GameManager = {
    generateDealerCards
  }

  def getCardDeckSize: Int = cardDeck.size

  def addCardTodealerHand(card: CardInterface): GameManager = {
    copy(dealerHand = dealerHand :+ card)
  }

  def getCard(index: Int): CardInterface = {
    dealerHand(index)
  }

  def getdealerHandSize: Int = {
    dealerHand.size
  }

  def getdealerHandValue: Int = {
    var v: Int = 0
    for {
      i <- dealerHand.indices
    } v += dealerHand(i).value
    v
  }

  def getLastdealerHandCard: CardInterface = {
    dealerHand.last
  }

  def containsCardType(rank: Ranks.Value): Int = {
    for (i <- dealerHand.indices) {
      if (dealerHand(i).rank == rank) {
        return i
      }
    }
    -1
  }
}
