package com.xkoranate.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        checkUser()
        checkCurrentGame()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuIcon.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_match_day -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.matchDayFragment2)
                }
                R.id.nav_result -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.homeFragment)
                }
                R.id.nav_league_table -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.leagueTableFragment)
                }
                R.id.nav_settings -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.settingsFragment)
                }
                R.id.nav_log_out -> {
                    // Log the user out of the app
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.nav_exit -> {
                    // Closes the app
                    finishAffinity()
                }

            }

            binding.drawerLayout.close()

            true
        }

    }

    // Todo: After persistence, update the isThereGame such that it checks from the viewModel if
    //  there is a currentGame

    private fun checkCurrentGame() {

        // Shared preferences to check if there is a current game. If there isn't, it starts
        // SetupGameActivity

        val isThereGame: Boolean =
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                .getBoolean("isThereGame", false)

        if (isThereGame) {
            // Stays in this activity
        } else {
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
                .putBoolean("isThereGame", true).apply()
            startActivity(Intent(this, SetupGameActivity::class.java))
        }


    }


    private fun checkUser() {
        // Todo: Checks if there is a current user, if there isn't, it opens login activity
    }

}
