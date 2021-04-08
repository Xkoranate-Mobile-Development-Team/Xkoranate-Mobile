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
import com.xkoranate.databinding.FragmentLoginBinding
import com.xkoranate.other.AuthListener
import com.xkoranate.ui.viewmodels.AuthViewModel
import com.xkoranate.ui.viewmodels.AuthViewModelFactory

class LoginFragment : Fragment(), AuthListener {

    private val factory = AuthViewModelFactory()

    private lateinit var viewModel: AuthViewModel

    var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        this.activity?.window?.decorView?.post {
            binding?.motionContainer?.apply {
                setTransition(R.id.begin, R.id.start)
                setTransitionDuration(1000)
                transitionToEnd()
            }
        }

        viewModel.authListener = this


        binding?.signUpButtonForLoginActivity?.setOnClickListener {
            Toast.makeText(activity, "👍👍👍", Toast.LENGTH_LONG).show()
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())

        }

        binding?.materialButton?.setOnClickListener {
            login(
                binding?.usernameTextLayout?.text.toString(),
                binding?.passwordLoginEditText?.text.toString()
            )
            Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(
                LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            )
        }

        return binding?.root

    }

    private fun login(email: String, password: String) {
        viewModel.login(email, password)

    }

    override fun onStarted() {

        binding?.motionContainer?.apply {
            setTransition(R.id.start, R.id.end)
            setTransitionDuration(1000)
            transitionToEnd()
        }


    }

    override fun onSuccess() {
        Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment).navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )
    }

    override fun onFailure(message: String) {
        binding?.progressBar?.visibility = View.INVISIBLE
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}
