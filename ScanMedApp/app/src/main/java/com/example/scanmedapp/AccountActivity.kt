package com.example.scanmedapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {

    private lateinit var etNewUsername: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        etNewUsername = findViewById(R.id.etNewUsername)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnSave = findViewById(R.id.btnSave)

        val currentUsername = intent.getStringExtra("username")
        etNewUsername.setText(currentUsername)

        btnSave.setOnClickListener {
            val newUsername = etNewUsername.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()

            Toast.makeText(this, "Zmieniono dane użytkownika!", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}
