package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.databinding.FragmentHomeBinding
import com.xkoranate.ui.viewmodels.participants.SharedViewModel


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        // The visibility for the result text view and the recycler view is set to gone
        // When there is something to display, the visibilities are changed and that of the welcome
        // image view is changed to gone

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)

        binding?.tvFullName?.text = viewModel.eventName

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}