package com.xkoranate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkoranate.databinding.FragmentLeagueTableBinding


class LeagueTableFragment : Fragment() {

    var binding: FragmentLeagueTableBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeagueTableBinding.inflate(inflater)


        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}