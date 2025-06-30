package com.example.scanmedapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvWelcome = findViewById(R.id.tvWelcome)
        val username = intent.getStringExtra("username")
        tvWelcome.text = "Hej, $username!"
    }
}
