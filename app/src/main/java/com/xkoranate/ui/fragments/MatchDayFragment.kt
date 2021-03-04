package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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
            binding?.progressBarMD?.visibility = View.VISIBLE
            binding?.btnStart?.visibility = View.INVISIBLE
            binding?.btnStop?.visibility = View.VISIBLE
        }

        binding?.btnStop?.setOnClickListener {
            // Game stops
            binding?.progressBarMD?.visibility = View.GONE
            binding?.btnStop?.visibility = View.INVISIBLE
            binding?.btnStart?.visibility = View.VISIBLE
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