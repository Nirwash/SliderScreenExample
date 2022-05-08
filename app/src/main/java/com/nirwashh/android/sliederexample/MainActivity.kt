package com.nirwashh.android.sliederexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.sliederexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}