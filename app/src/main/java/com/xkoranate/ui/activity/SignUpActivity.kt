package com.xkoranate.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.R
import com.xkoranate.databinding.ActivitySignupBinding
import com.xkoranate.other.AuthListener
import com.xkoranate.ui.viewmodels.AuthViewModel
import com.xkoranate.ui.viewmodels.AuthViewModelFactory

class SignUpActivity : AppCompatActivity(), AuthListener {

    private lateinit var signUpBinding: ActivitySignupBinding

    private val factory = AuthViewModelFactory()

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        signUpBinding.viewModel = viewModel
        viewModel.authListener = this
        
        // Enter transition
        overridePendingTransition(R.anim.right_to_left, R.anim.stay_put)

        signUpBinding.cancelButton.setOnClickListener {
//            Navigation.findNavController(t)
        }


    }

    override fun onStarted() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
