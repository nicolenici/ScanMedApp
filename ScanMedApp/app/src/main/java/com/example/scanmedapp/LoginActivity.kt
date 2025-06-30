package com.example.scanmedapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var layoutLoginForm: LinearLayout
    private lateinit var layoutRegisterForm: LinearLayout
    private lateinit var btnShowLogin: Button
    private lateinit var btnShowRegister: Button
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layoutLoginForm = findViewById(R.id.layoutLoginForm)
        layoutRegisterForm = findViewById(R.id.layoutRegisterForm)
        btnShowLogin = findViewById(R.id.btnShowLogin)
        btnShowRegister = findViewById(R.id.btnShowRegister)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        btnShowLogin.setOnClickListener {
            layoutLoginForm.visibility = View.VISIBLE
            layoutRegisterForm.visibility = View.GONE
        }

        btnShowRegister.setOnClickListener {
            layoutLoginForm.visibility = View.GONE
            layoutRegisterForm.visibility = View.VISIBLE
        }

        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.etLoginUsername).text.toString()
            val password = findViewById<EditText>(R.id.etLoginPassword).text.toString()

            val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
            val savedUsername = prefs.getString("username", "")
            val savedPassword = prefs.getString("password", "")

            if (username == savedUsername && password == savedPassword) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Błędny login lub hasło", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val username = findViewById<EditText>(R.id.etRegisterUsername).text.toString()
            val password = findViewById<EditText>(R.id.etRegisterPassword).text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE).edit()
                prefs.putString("username", username)
                prefs.putString("password", password)
                prefs.apply()

                Toast.makeText(this, "Rejestracja zakończona sukcesem", Toast.LENGTH_SHORT).show()
                layoutRegisterForm.visibility = View.GONE
                layoutLoginForm.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
