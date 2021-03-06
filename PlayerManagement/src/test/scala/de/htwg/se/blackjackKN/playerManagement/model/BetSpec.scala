package de.htwg.se.blackjackKN.playerManagement.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BetSpec extends WordSpec with Matchers {
  "A bet when" when {
    "new" should {
      "have the value it was assigned" in {
        val b : Bet = Bet(None, 1000)
        b.value should be (1000)
      }
      "return the value it was assigned, when calling getValue" in {
        val b : Bet = Bet(None, 1000)
        b.value should be (1000)
      }
    }
  }
}
