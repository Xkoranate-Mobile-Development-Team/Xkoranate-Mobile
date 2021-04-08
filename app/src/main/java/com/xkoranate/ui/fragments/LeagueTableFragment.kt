package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentLeagueTableBinding


class LeagueTableFragment : Fragment() {

    var binding: FragmentLeagueTableBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeagueTableBinding.inflate(inflater)

        binding?.toolbar?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                .popBackStack()
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}