package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.ui.activities.MainActivity


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetParticipantsBinding.inflate(inflater)

        binding?.btnContinue?.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }



        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}