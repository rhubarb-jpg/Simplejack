package com.example.blackjackapp.utilities

import com.example.blackjackapp.R
import com.example.blackjackapp.cardvalues.Card

object Images {
    lateinit var clubCardsImages :ArrayList<Int>
    lateinit var diamondCardsImages : ArrayList<Int>
    lateinit var heartCardsImages : ArrayList<Int>
    lateinit var  spadeCardsImages : ArrayList<Int>
    init{
        initializeCardImages()
    }
    fun getImage(card: Card) :Int{
        val x = card.number -1
        if(card.type == CardType.Club)
        {
            return clubCardsImages[x]
        }
        else if(card.type == CardType.Diamond)
        {
            return diamondCardsImages[x]
        }
        else if(card.type == CardType.Heart)
        {
            return heartCardsImages[x]
        }
        else if(card.type == CardType.Spade)
        {
            return spadeCardsImages[x]
        }
        return 0
    }

    fun initializeCardImages(){
        clubCardsImages = arrayListOf<Int>(
            R.drawable.clubs_ace ,
            R.drawable.clubs2,
            R.drawable.clubs3,
            R.drawable.clubs4,
            R.drawable.clubs5,
            R.drawable.clubs6,
            R.drawable.clubs7,
            R.drawable.clubs8,
            R.drawable.clubs9,
            R.drawable.clubs10,
            R.drawable.clubs_jack,
            R.drawable.clubs_queen,
            R.drawable.clubs_king
        )
        spadeCardsImages = arrayListOf<Int>(
            R.drawable.spades_ace ,
            R.drawable.spades2,
            R.drawable.spades3,
            R.drawable.spades4,
            R.drawable.spades5,
            R.drawable.spades6,
            R.drawable.spades7,
            R.drawable.spades8,
            R.drawable.spades9,
            R.drawable.spades10,
            R.drawable.spades_jack,
            R.drawable.spades_queen,
            R.drawable.spades_king
        )
        heartCardsImages = arrayListOf<Int>(
            R.drawable.hearts_ace ,
            R.drawable.hearts2,
            R.drawable.hearts3,
            R.drawable.hearts4,
            R.drawable.hearts5,
            R.drawable.hearts6,
            R.drawable.hearts7,
            R.drawable.hearts8,
            R.drawable.hearts9,
            R.drawable.hearts10,
            R.drawable.hearts_jack,
            R.drawable.hearts_queen,
            R.drawable.hearts_king
        )
        diamondCardsImages = arrayListOf<Int>(
            R.drawable.diamonds_ace ,
            R.drawable.diamonds2,
            R.drawable.diamonds3,
            R.drawable.diamonds4,
            R.drawable.diamonds5,
            R.drawable.diamonds6,
            R.drawable.diamonds7,
            R.drawable.diamonds8,
            R.drawable.diamonds9,
            R.drawable.diamonds10,
            R.drawable.diamonds_jack,
            R.drawable.diamonds_queen,
            R.drawable.diamonds_king
        )
    }
}