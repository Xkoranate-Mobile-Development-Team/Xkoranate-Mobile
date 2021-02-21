package com.xkoranate.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
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
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.matchDayFragment2)
                }
                R.id.nav_result -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.homeFragment)
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

            }

            binding.drawerLayout.close()

            true
        }

    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 1) {
            finishAffinity()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
