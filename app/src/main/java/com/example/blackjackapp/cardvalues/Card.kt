package com.example.blackjackapp.cardvalues

import com.example.blackjackapp.utilities.CardType

class Card (number: Int, type: CardType){
    var number : Int
    var type: CardType

    init{
        this.number = number
        this.type = type
    }
}