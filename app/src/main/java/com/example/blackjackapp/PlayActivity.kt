package com.example.blackjackapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import com.example.blackjackapp.cardvalues.Hand
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.*
import com.example.blackjackapp.utilities.Uni
import com.example.blackjackapp.utilities.Images
import com.example.blackjackapp.utilities.Util
import com.example.blackjackapp.cardvalues.Deck

class PlayActivity : AppCompatActivity() {
    private lateinit var deck : Deck
    private lateinit var dealer_hand : Hand
    private lateinit var player_hand : Hand
    var currentBetValue = 0
    var currentBalanceValue = 0
    lateinit var bitmapOptions : BitmapFactory.Options
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        val initialValue = 500
        val bal = findViewById<TextView>(R.id.balanceValue)
        val bet = findViewById<TextView>(R.id.betValue)
        val dealButton = findViewById<Button>(R.id.buttonDeal)
        val dealLayout = findViewById<LinearLayout>(R.id.layout_deal)
        val gameLayout = findViewById<LinearLayout>(R.id.layout_gameCommand)
        val dealerHand = findViewById<LinearLayout>(R.id.dealerHandLayout)
        val playerHand = findViewById<LinearLayout>(R.id.playerHandLayout)
        val playerScore = findViewById<TextView>(R.id.playerHandScore)
        val dealerScore = findViewById<TextView>(R.id.dealerHandScore)
        val clear = findViewById<Button>(R.id.buttonClear)
        val hit = findViewById<Button>(R.id.buttonHit)
        val stand = findViewById<Button>(R.id.buttonStand)
        val bet5 = findViewById<ImageView>(R.id.bet5)
        val bet10 = findViewById<ImageView>(R.id.bet10)
        val bet20 = findViewById<ImageView>(R.id.bet20)
        val bet50 = findViewById<ImageView>(R.id.bet50)
        val bet100 = findViewById<ImageView>(R.id.bet100)
        val homeClick = findViewById<Button>(R.id.homeButton3)
        homeClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bal.text = initialValue.toString()
        deck = Deck()
        bet.text = "0"
        bitmapOptions = BitmapFactory.Options().apply {
            inSampleSize = 2
        }
        startGame()
        dealButton.setOnClickListener{
            dealLayout.isVisible=false
            gameLayout.isVisible=true
            dealerHand.removeAllViews()
            playerHand.removeAllViews()
            currentBetValue = bet.text.toString().toInt()
            currentBalanceValue = bal.text.toString().toInt()

            val newBalanceValue = currentBalanceValue - currentBetValue
            if(newBalanceValue < 0)
            {
                alert(getString(R.string.noBalance))
                return@setOnClickListener
            }
            bal.text  = newBalanceValue.toString()
            Thread {
                player_hand.insert(deck.drawCard())
                runOnUiThread {
                    playerScore.text = player_hand.value.toString()
                    showCard(player_hand, playerHand)
                }
                Thread.sleep(Uni.dealSpeed)
                dealer_hand.insert(deck.drawCard())
                runOnUiThread {
                    dealerScore.text = dealer_hand.value.toString()
                    showCard(dealer_hand, dealerHand)
                }
                Thread.sleep(Uni.dealSpeed)
                player_hand.insert(deck.drawCard())
                runOnUiThread {
                    playerScore.text = player_hand.value.toString()
                    showCard(player_hand, playerHand)
                }
                Thread.sleep(Uni.dealSpeed)
                runOnUiThread {
                    closeCard()
                }
                Thread.sleep(Uni.dealSpeed)
                if(player_hand.value == 21 && player_hand.cardList.count() == 2)
                {
                    //This is for first round 21
                    runOnUiThread {
                        alert(getString(R.string.win))
                        bal.text  = (currentBalanceValue + currentBetValue).toString()
                    }
                }
            }.start()
        }
        clear.setOnClickListener {
            bet.text = "0"
        }
        hit.setOnClickListener{
            player_hand.insert(deck.drawCard())
            playerScore.text = player_hand.value.toString()
            showCard(player_hand, playerHand)
            Thread{
                if (player_hand.value > 21) {

                    Thread.sleep(Uni.dealSpeed)
                    runOnUiThread {
                        alert(getString(R.string.b_deal))
                    }
                }
            }.start()
        }
        stand.setOnClickListener{
            Thread {
                while(dealer_hand.value <17) {
                    dealer_hand.insert(deck.drawCard())
                    runOnUiThread {
                        showCard(dealer_hand, dealerHand)
                        dealerScore.text = dealer_hand.value.toString()
                    }
                    Thread.sleep(Uni.dealSpeed)
                }
                runOnUiThread {
                    if (dealer_hand.value > 21) {
                        alert(getString(R.string.win))
                        bal.text  = (currentBalanceValue + currentBetValue).toString()
                    }
                    else
                    {
                        if(dealer_hand.value== player_hand.value)
                        {
                            alert(getString(R.string.push))
                            bal.text  = (currentBalanceValue).toString()
                        }
                        else if(dealer_hand.value < player_hand.value)
                        {
                            alert(getString(R.string.win))
                            bal.text  = (currentBalanceValue + currentBetValue).toString()
                        }
                        else {
                            alert(getString(R.string.b_deal))
                        }
                    }
                }
            }.start()
        }
        bet5.setOnClickListener {
            val value = 5 + bet.text.toString().toInt()
            bet.text = value.toString()
        }
        bet10.setOnClickListener {
            val value = 10 + bet.text.toString().toInt()
            bet.text = value.toString()
        }
        bet20.setOnClickListener {
            val value = 20 + bet.text.toString().toInt()
            bet.text = value.toString()
        }
        bet50.setOnClickListener {
            val value = 50 + bet.text.toString().toInt()
            bet.text = value.toString()
        }
        bet100.setOnClickListener {
            val value = 100 + bet.text.toString().toInt()
            bet.text = value.toString()
        }
    }
    private fun alert(message: String){
        val x_builder = AlertDialog.Builder(this)
        x_builder.setTitle("")
        x_builder.setMessage(message)
        x_builder.setPositiveButton(android.R.string.ok){_, _ ->
            startGame()
        }
        x_builder.show()
    }
    private fun showCard(card:Bitmap, layout: LinearLayout){
        val view = ImageView(this)
        view.setImageBitmap(card)
        view.adjustViewBounds = true
        val c_w = 80
        val c_h = 120
        val params = LinearLayout.LayoutParams(layout.getLayoutParams())
        if (layout.size == 0){
            params.setMargins(0,0,0,0)
        }else {
            params.setMargins(Util.convertDpToPixel(-c_w.toFloat() / 2, this).toInt(),0,0,0)
        }
        view.maxWidth = Util.convertDpToPixel(c_w.toFloat(), this).toInt()
        view.maxHeight = Util.convertDpToPixel(c_h.toFloat(), this).toInt()
        layout.addView(view)
    }

    private fun showCard(x: Hand, y: LinearLayout) {
        y.removeAllViews()
        x.cardList.forEach()
        {
            showCard( BitmapFactory.decodeResource(resources, Images.getImage(it), bitmapOptions), y)
        }
    }

    private fun closeCard(){
        val dealer_hand = findViewById<View>(R.id.dealerHandLayout)
        showCard(BitmapFactory.decodeResource(resources, R.drawable.back),
            dealer_hand as LinearLayout
        )
    }

    private fun startGame(){
        dealer_hand=Hand()
        player_hand=Hand()
        val layout_deal = findViewById<LinearLayout>(R.id.layout_deal)
        val layout_gameCommand = findViewById<LinearLayout>(R.id.layout_gameCommand)
        layout_deal.isVisible=true
        layout_gameCommand.isVisible=false
    }

}