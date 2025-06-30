package com.example.scanmedapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(username, password)
            }
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(username, password)
            }
        }
    }

    private fun registerUser(username: String, password: String) {
        val sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE)
        if (sharedPref.contains(username)) {
            Toast.makeText(this, "Użytkownik już istnieje", Toast.LENGTH_SHORT).show()
        } else {
            sharedPref.edit().putString(username, password).apply()
            Toast.makeText(this, "Zarejestrowano pomyślnie", Toast.LENGTH_SHORT).show()
            openMainActivity(username)
        }
    }

    private fun loginUser(username: String, password: String) {
        val sharedPref = getSharedPreferences("users", Context.MODE_PRIVATE)
        val storedPassword = sharedPref.getString(username, null)
        if (storedPassword == null) {
            Toast.makeText(this, "Użytkownik nie istnieje", Toast.LENGTH_SHORT).show()
        } else if (storedPassword == password) {
            Toast.makeText(this, "Zalogowano pomyślnie", Toast.LENGTH_SHORT).show()
            openMainActivity(username)
        } else {
            Toast.makeText(this, "Niepoprawne hasło", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openMainActivity(username: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
}
