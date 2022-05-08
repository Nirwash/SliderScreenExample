package com.nirwashh.android.sliederexample

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.viewpager.widget.PagerAdapter

const val APP_SETTINGS = "App settings"
const val IS_STARTED_UP = "Is started app"

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val preferences = getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)
        val flag = preferences.contains(IS_STARTED_UP)
        if (!flag) {
            val editor = preferences.edit()
            editor.putBoolean(IS_STARTED_UP, true).apply()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}