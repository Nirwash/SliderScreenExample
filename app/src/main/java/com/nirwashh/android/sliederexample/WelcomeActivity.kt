package com.nirwashh.android.sliederexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.sliederexample.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}