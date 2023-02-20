package com.example.cs427_advanced_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val RegisterNav: TextView = findViewById(R.id.RegisterNav)

        val Login: Button = findViewById(R.id.buttonLogin)

        RegisterNav.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        Login.setOnClickListener {
            performLogin();
        }
    }

    private fun performLogin() {
        val EmailText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val PasswordText: EditText = findViewById(R.id.editTextTextPassword)

        val inputEmail = EmailText.text.toString();
        val inputPassword = PasswordText.text.toString();

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Authentication Success.",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

}