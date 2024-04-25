package com.example.blackjackapp.cardvalues

import com.example.blackjackapp.utilities.CardType

class Deck {
    var cardList = arrayListOf<Card>()

    init {
        initializeDeck()
    }
    private fun initializeDeck(){
        for (i in 1 .. 13){
            cardList.add(Card(i, CardType.Diamond))
            cardList.add(Card(i, CardType.Club))
            cardList.add(Card(i, CardType.Heart))
            cardList.add(Card(i, CardType.Spade))
            cardList.shuffle()
        }
    }
    fun drawCard(): Card{
        val card = cardList.elementAt(0)
        cardList.removeAt(0)
        if(cardList.count()==0){
            initializeDeck()
        }
        return card
    }
}