package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSettingsBinding
import com.xkoranate.ui.activities.SetupGameActivity
import com.xkoranate.ui.viewmodels.SharedViewModel


class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)

        binding = FragmentSettingsBinding.inflate(inflater)

        binding?.newGame?.setOnClickListener {

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle("New Game")
                .setView(R.layout.dialog_new_game)
                .setNegativeButton("Cancel") { dialog, which ->
                    // Responds to cancel button
                }
                .setPositiveButton("Yes") { dialog, which ->
                    viewModel.deleteGame()
                    viewModel.delete()
                    startActivity(Intent(activity, SetupGameActivity::class.java))
                }

            dialog.show()
        }

        binding?.darkModeSwitch?.setOnClickListener {
            if (binding?.darkModeSwitch!!.isChecked) {

            }
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun darkMode() {

    }

}