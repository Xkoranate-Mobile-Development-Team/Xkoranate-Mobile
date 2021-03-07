package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentMatchDayBinding


class MatchDayFragment : Fragment() {

    private var binding: FragmentMatchDayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDayBinding.inflate(inflater)

        binding?.btnCheckResults?.setOnClickListener {
            if (binding?.progressBarMD?.visibility == View.VISIBLE) {
                Toast.makeText(activity, getString(R.string.game_in_progress), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_matchDayFragment2_to_homeFragment)
            }

        }

        binding?.btnStart?.setOnClickListener {
            // Game starts
            Toast.makeText(activity, getString(R.string.match_in_progress), Toast.LENGTH_SHORT)
                .show()
            binding?.progressBarMD?.visibility = View.VISIBLE
            binding?.btnStart?.visibility = View.INVISIBLE
            binding?.btnStop?.visibility = View.VISIBLE
        }

        binding?.btnStop?.setOnClickListener {
            // Game stops

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle(getString(R.string.stop_match))
                .setView(R.layout.dialog_cancel_game)
                .setNegativeButton("No") { _, _ ->
                    // Closes the dialog itself
                }
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(activity, getString(R.string.match_canceled), Toast.LENGTH_SHORT)
                        .show()
                    binding?.progressBarMD?.visibility = View.GONE
                    binding?.btnStop?.visibility = View.INVISIBLE
                    binding?.btnStart?.visibility = View.VISIBLE
                }
            dialog.show()

        }




        return binding?.root
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