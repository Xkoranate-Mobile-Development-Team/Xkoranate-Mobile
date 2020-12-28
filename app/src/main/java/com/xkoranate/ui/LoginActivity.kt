package com.xkoranate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.R
import com.xkoranate.other.AuthListener
import com.xkoranate.ui.viewmodels.AuthViewModel
import com.xkoranate.ui.viewmodels.AuthViewModelFactory

class LoginActivity : AppCompatActivity(), AuthListener {

    private val factory = AuthViewModelFactory()

    private lateinit var viewModel: AuthViewModel

    private lateinit var motionContainer: MotionLayout
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditext: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        motionContainer = findViewById(R.id.motionContainer)
        usernameEditText = findViewById(R.id.usernameTextLayout)
        passwordEditext = findViewById(R.id.passwordLoginEditText)
        loginButton = findViewById(R.id.materialButton)
        signUpButton = findViewById(R.id.signUpButtonForLoginActivity)
        progressBar = findViewById(R.id.progress_bar)

        Log.i("Lol", loginButton.hashCode().toString())

        window.decorView.post {
            motionContainer.apply {
                setTransition(R.id.begin, R.id.start)
                setTransitionDuration(1000)
                transitionToEnd()
            }
        }

        viewModel.authListener = this


        signUpButton.setOnClickListener {
            Toast.makeText(this, "Okayyy", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            login(usernameEditText.text.toString(), passwordEditext.text.toString())
        }

    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password)

    }

    override fun onStarted() {

        motionContainer.apply {
            setTransition(R.id.start, R.id.end)
            setTransitionDuration(1000)
            transitionToEnd()
        }


    }

    override fun onSuccess() {

        Intent(this, MainScreenActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }
}




