package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentHomeBinding
import com.xkoranate.ui.viewmodels.SharedViewModel


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        binding?.menuIcon?.setOnClickListener {
            binding?.drawerLayout?.open()
        }


        binding?.navView?.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_match_day -> {
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.matchDayFragment2)
                }
                R.id.nav_result -> {
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.homeFragment)
                }
                R.id.nav_league_table -> {
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.leagueTableFragment)
                }
                R.id.nav_settings -> {
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.settingsFragment)
                }
                R.id.nav_log_out -> {
                    // Log the user out of the app
                    this.activity?.startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }
                R.id.nav_exit -> {
                    // Closes the app
                    activity?.finishAffinity()
                }

            }

            binding?.drawerLayout?.close()

            true
        }

        // The visibility for the result text view and the recycler view is set to gone
        // When there is something to display, the visibilities are changed and that of the welcome
        // image view is changed to gone

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)



        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}