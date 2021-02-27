package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.databinding.FragmentSettingsBinding
import com.xkoranate.ui.activities.SetupGameActivity
import com.xkoranate.ui.viewmodels.SettingsFragmentViewModel

private lateinit var viewModel: SettingsFragmentViewModel

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater)
//        viewModel = ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)

        binding?.newGame?.setOnClickListener {

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle("New Game")
                .setMessage("Do you want to start a new game? This will clear current game")
                .setNegativeButton("Cancel") { dialog, which ->
                    // Responds to cancel button
                }
                .setPositiveButton("Yes") { dialog, which ->
                    // Todo: Clear the current game
                    startActivity(Intent(activity, SetupGameActivity::class.java))
                }

            dialog.show()
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}