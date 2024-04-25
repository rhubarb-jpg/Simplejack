package com.example.blackjackapp.cardvalues

class Hand {
    var cardList = arrayListOf<Card>() //Holds the cards of a hand.
    var value : Int = 0   //Holds the hand score.
    private var mayReduceAceValue : Boolean = false //Holds if there exists and ACE card in an hand.

    //Triggered when a player requests a card.
    fun insert(card : Card) {
        cardList.add(card)
        calculateScore(card)
    }
    fun calculateScore(card: Card) {
        val cardNumber = card.number
        if(cardNumber == 1 && value<11){
            value=value+11
            mayReduceAceValue = true
        }
        else if(cardNumber<=10) {
            value=value+cardNumber
        }
        else {
            value=value+10
        }
        if(value>21) {
            if(mayReduceAceValue) {
                value = value - 10
                mayReduceAceValue = false
            }
        }
    }
}