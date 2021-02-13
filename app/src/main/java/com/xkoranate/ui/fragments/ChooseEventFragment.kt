package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentChooseEventBinding

class ChooseEventFragment : Fragment() {

    private var binding: FragmentChooseEventBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseEventBinding.inflate(inflater)

        // The values of the variables below would be updated from the spinner values
        var isIndividual = true
        var isRoundRobin = false

        binding?.continueButtonChooseEvent?.setOnClickListener {
            if (isIndividual) {
                Navigation.findNavController(it)
                    .navigate(R.id.action_chooseEventFragment_to_individualEventFragment)
            }
            if (isRoundRobin) {
                Navigation.findNavController(it)
                    .navigate(R.id.action_chooseEventFragment_to_roundRobinFragment)
            }
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}