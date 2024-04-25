package com.example.blackjackapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //alarmService notification


        //Music
        val ring: MediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.ring)
        //ring.isLooping()
        ring.start()

        //Settings button
        val settingsClick = findViewById<Button>(R.id.settingsButton)
        settingsClick.setOnClickListener{
            ring.pause()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        //PLay Button
        val playClick = findViewById<Button>(R.id.playButton)
        playClick.setOnClickListener{
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }
        // Leaderboard Button
        val leaderboardClick = findViewById<Button>(R.id.leaderboardButton)
        leaderboardClick.setOnClickListener{
            ring.stop()
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }

}


