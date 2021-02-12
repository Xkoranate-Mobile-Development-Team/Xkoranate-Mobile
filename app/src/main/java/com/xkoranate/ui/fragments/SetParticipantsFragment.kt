package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkoranate.databinding.FragmentSetParticipantsBinding


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetParticipantsBinding.inflate(inflater)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SetParticipantsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}