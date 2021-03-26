package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentIndividualEventBinding
import com.xkoranate.ui.viewmodels.SharedViewModel

class IndividualEventFragment : Fragment() {

    private var binding: FragmentIndividualEventBinding? = null
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndividualEventBinding.inflate(inflater)

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)

        binding?.allowDraws?.setOnClickListener {
            if (binding?.allowDraws?.isChecked == true) {
                viewModel.allowDraws(true)
            }
        }

        binding?.continueButtonIndividualEvent?.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_individualEventFragment_to_setParticipantsFragment)
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}