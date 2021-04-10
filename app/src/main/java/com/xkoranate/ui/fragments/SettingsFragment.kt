package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSettingsBinding
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

        binding?.toolbar?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                .popBackStack()
        }

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
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(
                            SettingsFragmentDirections.actionSettingsFragmentToChooseEventFragment2()
                        )
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