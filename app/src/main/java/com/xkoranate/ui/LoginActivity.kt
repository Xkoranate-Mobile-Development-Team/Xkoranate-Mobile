package com.xkoranate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.R
import com.xkoranate.databinding.ActivityLoginBinding
import com.xkoranate.other.AuthListener
import com.xkoranate.ui.viewmodels.AuthViewModel
import com.xkoranate.ui.viewmodels.AuthViewModelFactory

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var loginBinding: ActivityLoginBinding

    private val factory = AuthViewModelFactory()

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

//        window.decorView.post {
//            loginBinding.motionContainer.apply {
//                setTransition(R.id.begin, R.id.start)
//                setTransitionDuration(1000)
//                transitionToEnd()
//            }
//        }

        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)




        loginBinding.viewModel = viewModel
        viewModel.authListener = this


        loginBinding.signUpButtonForLoginActivity.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStarted() {
        loginBinding.progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        Intent(this, MainScreenActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        loginBinding.progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}




