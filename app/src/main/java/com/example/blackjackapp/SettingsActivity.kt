package com.example.blackjackapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity() {
    //private var btnToggleDark: Button? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        //Val for Darkmde
        val btnToggleDark = findViewById<Button>(R.id.btnToggleDark)


        //Val for seekbar


        //For song
        val ring: MediaPlayer = MediaPlayer.create(this@SettingsActivity, R.raw.ring)
        ring.start()

        val homeClick = findViewById<Button>(R.id.homeButton)
        homeClick.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            ring.pause()
        }

            //Separator (shared preferences)

            val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)

            //Reopening app
            if (isDarkModeOn) {
                ring.pause()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                btnToggleDark.setText("Disable Dark Mode")

            } else {
                ring.pause()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                btnToggleDark.setText("Enable Dark Mode")
               // ring.stop()
            }
            btnToggleDark.setOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(view: View?) {

                        val dialogBinding = layoutInflater.inflate(R.layout.darkmode_dialog, null)
                        val myDialog = Dialog(this@SettingsActivity)
                        myDialog.setContentView(dialogBinding)
                        myDialog.setCancelable(true)
                        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        myDialog.show()
                        val yesbtn = dialogBinding.findViewById<Button>(R.id.alert_yes)
                        yesbtn.setOnClickListener{
                            myDialog.dismiss()
                        }

                        ring.stop()
                        if (isDarkModeOn) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            editor.putBoolean(
                                "isDarkModeOn", false
                            )


                            editor.apply()
                            btnToggleDark.setText("Enabele Dark Mode")
                        } else {
                            AppCompatDelegate
                                .setDefaultNightMode(
                                    AppCompatDelegate.MODE_NIGHT_YES
                                )



                            editor.putBoolean(
                                "isDarkModeOn", true
                            )
                            editor.apply()
                            btnToggleDark.setText(
                                "Disable Dark Mode"
                            )
                        }
                    }
                })

        //Dialog Added

        }
}





