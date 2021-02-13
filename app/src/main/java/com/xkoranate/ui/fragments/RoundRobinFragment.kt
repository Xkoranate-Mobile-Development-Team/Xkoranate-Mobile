package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentRoundRobinBinding

class RoundRobinFragment : Fragment() {

    private var binding: FragmentRoundRobinBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoundRobinBinding.inflate(inflater)

        binding?.continueButtonRoundRobinEvent?.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_roundRobinFragment_to_setParticipantsFragment)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}