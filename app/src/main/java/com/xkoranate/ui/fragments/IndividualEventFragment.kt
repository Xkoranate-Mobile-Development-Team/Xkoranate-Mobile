package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.xkoranate.databinding.FragmentIndividualEventBinding
import com.xkoranate.ui.viewmodels.SharedViewModel


class IndividualEventFragment : Fragment() {

    private var binding: FragmentIndividualEventBinding? = null
    private lateinit var viewModel: SharedViewModel

    var allowDraw = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndividualEventBinding.inflate(inflater)

        val args = arguments?.let { IndividualEventFragmentArgs.fromBundle(it) }
        if (args != null) {
            sportSelected = args.sportSelected
        }

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)

        binding?.allowDraws?.setOnClickListener {
            allowDraw = binding?.allowDraws?.isChecked == true
        }

        binding?.continueButtonIndividualEvent?.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    IndividualEventFragmentDirections.actionIndividualEventFragmentToSetParticipantsFragment(
                        sportSelected, allowDraw
                    )
                )
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}