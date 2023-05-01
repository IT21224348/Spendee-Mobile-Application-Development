package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.spendee.databinding.ActivityLoginBinding
import com.example.spendee.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.IntSummaryStatistics

class Login : AppCompatActivity() {

    //Declare variables
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get a instance of the firebase authentication
        auth = Firebase.auth


        //Attach a click listener to the "Log in" button using view binding
        binding.loginBtn.setOnClickListener {
            //Perform login logic here
            val email =binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            if(checkAllfields()){
               auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                   if(it.isSuccessful){
                       Toast.makeText(this,"Successfully signed in",Toast.LENGTH_SHORT).show()

                       // Get the current user's email and Firebase ID
                       val userEmail = auth.currentUser?.email.toString()
                       val firebaseId = auth.currentUser?.uid.toString()


                       //Navigate to another activity
                       val intent =Intent(this,Home::class.java)
                       intent.putExtra("userEmail", userEmail)
                       intent.putExtra("firebaseId", firebaseId)
                       startActivity(intent)
                   }else{
                       Log.e("error: ", it.exception.toString())
                       Toast.makeText(this, "Check your login credentials", Toast.LENGTH_SHORT).show()

                   }
               }
            }
        }



        //Attach a click listener to the "Register"  text using view binding
        binding.loginRegister.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun checkAllfields():Boolean{
        //Get the text from input fields
        val email = binding.loginEmail.text.toString().trim()
        val password = binding.loginPassword.text.toString().trim()

        if(email.isEmpty()){
            //Email field is empty,show a error message
            binding.loginEmail.error = getString(R.string.error_email_empty)
            return false
        }

        if(password.isEmpty()){
            //Password field is empty,show a error message
            binding.loginPassword.error = getString(R.string.error_pwd_empty)
            return false
        }

        //Check email format
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.loginEmail.error = getString(R.string.error_email_format)
            return false
        }

        //All fields are non empty
        return true
    }

}