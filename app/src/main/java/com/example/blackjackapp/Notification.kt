package com.example.blackjackapp

import android.app.Application;
import com.onesignal.OneSignal



class Notification: Application(){
    private val NOTIFICATION_ID = "86691be5-3e40-477d-a1b7-be69ceeb29e0"
    override fun onCreate(){
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(NOTIFICATION_ID)
    }
}