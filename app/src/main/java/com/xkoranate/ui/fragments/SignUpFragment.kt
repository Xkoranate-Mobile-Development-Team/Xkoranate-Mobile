package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSignUpBinding
import com.xkoranate.other.AuthListener
import com.xkoranate.ui.viewmodels.AuthViewModel
import com.xkoranate.ui.viewmodels.AuthViewModelFactory

class SignUpFragment : Fragment(), AuthListener {

    private val factory = AuthViewModelFactory()

    private lateinit var viewModel: AuthViewModel

    var binding: FragmentSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding?.viewModel = viewModel
        viewModel.authListener = this

        binding?.cancelButton?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                .popBackStack()
        }

        return binding?.root
    }

    override fun onStarted() {
        Toast.makeText(activity, "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(
            SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
        )
    }

    override fun onFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
