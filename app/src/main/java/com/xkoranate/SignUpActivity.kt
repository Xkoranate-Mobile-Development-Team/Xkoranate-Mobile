package com.xkoranate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xkoranate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        signUpBinding.cancelButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

//    private fun View.hideKeyboard() {
//        val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
//    }
}
