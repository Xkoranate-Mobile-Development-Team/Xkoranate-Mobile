package com.xkoranate.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xkoranate.R
import com.xkoranate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuIcon.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_match_day -> {
//                    Navigation.findNavController().navigate(R.id.action_homeFragment_to_matchDayFragment2)
                }
                R.id.nav_result -> {
                    // Stays in the same fragment
                }
                R.id.nav_settings -> {
//                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_settingsFragment)
                }
                R.id.nav_log_out -> {
                    // Log the user out of the app
                }

            }

            binding.drawerLayout.close()

            true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
