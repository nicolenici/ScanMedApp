package com.example.scanmedapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.scanmedapp.AccountActivity
import com.example.scanmedapp.R
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val username = prefs.getString("username", "Użytkowniku") // domyślnie "Użytkowniku"

        tvWelcome.text = "Witaj, $username!"

        val btnAccount = findViewById<Button>(R.id.btnAccount)
        btnAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}