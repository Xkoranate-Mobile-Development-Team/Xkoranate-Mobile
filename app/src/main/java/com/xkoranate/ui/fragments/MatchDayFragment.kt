package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_matchDayFragment2_to_homeFragment)
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