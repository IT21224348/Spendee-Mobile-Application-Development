package com.example.spendee

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.example.spendee.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Signup : AppCompatActivity() {
    @SuppressLint("WrongViewCast")

    //Declaring Varables
     private lateinit var auth:FirebaseAuth
     private lateinit var binding: SignupBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // Attach a click listener to the "Sign Up" button using view binding
        binding.btnSignup.setOnClickListener {
        // Perform sign up logic here
          val email = binding.userEmail.text.toString()
          val password = binding.password.text.toString()
            if (checkAllfields()){
                FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val signInMethods = task.result?.signInMethods
                            if (signInMethods.isNullOrEmpty()) {
                                // Email not registered yet, proceed with sign up
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { signUpTask ->
                                        if (signUpTask.isSuccessful) {
                                            // Account created successfully
                                            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                                            // Navigate to login activity
                                            startActivity(Intent(this, Login::class.java))
                                            finish()
                                        } else {
                                            // Account not created
                                            Log.e("error: ", signUpTask.exception.toString())
                                            Toast.makeText(this, "Account not created successfully", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } else {
                                // Email already registered, display error message
                               // binding.userEmail.error = getString(R.string.error_email_taken)
                                Toast.makeText(this, "The email address you entered is already registered. Please use a different email address.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            // Error occurred while checking email, display error message
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }



            }

        }
        // Attach a click listener to the "Sign in" text using view binding
        binding.registerLogin.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


        }
    private fun checkAllfields():Boolean{
        //Get the text from the input fields
        val username = binding.userName.text.toString().trim()
        val email = binding.userEmail.text.toString().trim()
        val password = binding.password.text.toString().trim()
        val cnf_password = binding.cnfpassword.text.toString().trim()


        //Check whether any fields are empty
        if(username.isEmpty()){
             //Username field is empty, show a error message
            binding.userName.error = getString(R.string.error_username_empty)
            return false
        }

        if(email.isEmpty()){
            //Email field is empty, show a error message
            binding.userEmail.error = getString(R.string.error_email_empty)
            return false
        }


        if(password.isEmpty()){
            //Password field is empty, show a error message
            binding.password.error  = getString(R.string.error_pwd_empty)
            return false
        }

        if(cnf_password.isEmpty()){
            //Confirm password field is empty, show a error message
            binding.cnfpassword.error = getString(R.string.error_pwd_empty)
            return false
        }

        //Check whether password and confirm password fields are equal
        if(password != cnf_password){
            binding.cnfpassword.error = getString(R.string.error_pwd_not_match)
            return false
        }

        //check whether password is at least  characters
        if (password.length < 6){
            binding.password.error = getString(R.string.error_pwd_less_characters)
            return false

        }

        //Check email format
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.userEmail.error = getString(R.string.error_email_format)
            return false

        }




        //All fields are non empty
        return true
    }





}






