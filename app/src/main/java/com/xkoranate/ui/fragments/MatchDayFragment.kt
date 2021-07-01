package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.xkoranate.R
import com.xkoranate.databinding.FragmentMatchDayBinding
import com.xkoranate.ui.viewmodels.SharedViewModel

class MatchDayFragment : Fragment() {

    private var binding: FragmentMatchDayBinding? = null
    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDayBinding.inflate(inflater)

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(SharedViewModel::class.java)

        binding?.matchViewModel = viewModel

        binding?.lifecycleOwner = this

        binding?.btnCheckResults?.setOnClickListener {
            if (binding?.progressBarMD?.visibility == View.VISIBLE) {
                Toast.makeText(activity, getString(R.string.game_in_progress), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.homeFragment)
            }
        }

        binding?.toolbar?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                .popBackStack()
        }

        binding?.btnStart?.setOnClickListener {
            // Game starts
            startCountDown()

            binding?.root?.let { Snackbar.make(it, "Match started", Snackbar.LENGTH_SHORT).show() }
            binding?.progressBarMD?.visibility = View.VISIBLE
            binding?.btnStart?.visibility = View.INVISIBLE
            binding?.btnStop?.visibility = View.VISIBLE
        }

        binding?.btnStop?.setOnClickListener {
            // Game stops
            stopCountdown()

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle(getString(R.string.stop_match))
                .setView(R.layout.dialog_cancel_game)
                .setNegativeButton("No") { _, _ ->
                    // Closes the dialog itself
                }
                .setPositiveButton("Yes") { _, _ ->
                    binding?.root?.let {
                        Snackbar.make(it, "Match canceled", Snackbar.LENGTH_SHORT).show()
                    }
                    binding?.progressBarMD?.visibility = View.GONE
                    binding?.btnStop?.visibility = View.INVISIBLE
                    binding?.btnStart?.visibility = View.VISIBLE
                }
            dialog.show()
        }

        return binding?.root
    }

    private fun startCountDown() {
        viewModel.startTimer()
    }

    private fun stopCountdown() {
        // Todo
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MatchDayFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
