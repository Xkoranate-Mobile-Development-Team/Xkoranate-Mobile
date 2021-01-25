package com.xkoranate.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xkoranate.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remember to set the launcher activity back to main activity in manifest

        val isFirstRun: Boolean =
                getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

        if (isFirstRun) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
//            startActivity(Intent(this, MainScreenActivity::class.java))
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply()
    }
}
