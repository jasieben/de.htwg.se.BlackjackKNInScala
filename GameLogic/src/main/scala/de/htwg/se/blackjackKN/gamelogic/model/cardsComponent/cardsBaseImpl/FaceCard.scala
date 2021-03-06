package de.htwg.se.blackjackKN.gamelogic.model.cardsComponent.cardsBaseImpl

import de.htwg.se.blackjackKN.gamelogic.model.{Ranks, Suits}

case class FaceCard(suit: Suits.Value = Suits.Clubs, rank: Ranks.Value = Ranks.Ace, isLowValueAce: Boolean = false) extends Card {
  val ranks: List[(Ranks.Value, Int)] = List((Ranks.King, 10), (Ranks.Queen, 10), (Ranks.Jack, 10), (Ranks.Ace, 11))

  require(suits.contains(suit))
  require(ranks.exists(_._1 == rank))

  lazy val value: Int = if (isLowValueAce) 1 else ranks.find(_._1 == rank).get._2

}
