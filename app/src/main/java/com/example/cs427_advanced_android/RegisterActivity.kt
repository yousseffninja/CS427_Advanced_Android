package com.example.cs427_advanced_android

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val LoginNav: TextView = findViewById(R.id.rlog)
        val Register: Button = findViewById(R.id.buttonRegister)

        LoginNav.setOnClickListener {
            finish()
        }

        Register.setOnClickListener {
            performSignUp();
        }
    }

    private fun performSignUp() {
        val firstName: EditText = findViewById(R.id.firstname)
        val lastName: EditText = findViewById(R.id.lastname)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.passowrd)
        val confirmPassword: EditText = findViewById(R.id.confirm_password)

        val inputFirstName = firstName.text.toString()
        val inputInputLastName = lastName.text.toString()
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        val inputConfirmPassword = confirmPassword.text.toString()

        if (inputPassword == inputConfirmPassword){
            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        CreateUser(inputFirstName, inputInputLastName, inputEmail)
                        Toast.makeText(this, "Authentication Success\n Please Login", Toast.LENGTH_SHORT).show()
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun CreateUser(inputFirst: String, inputLast: String,
                           inputEmail: String) {
        val user = hashMapOf(
            "first name" to inputFirst,
            "last last" to inputLast,
            "email" to inputEmail
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}