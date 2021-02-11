package com.xkoranate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xkoranate.R
import com.xkoranate.databinding.FragmentHomeBinding
import com.xkoranate.databinding.FragmentMatchDayBinding
import com.xkoranate.databinding.FragmentSetParticipantsBinding


class MatchDayFragment : Fragment() {

    private var binding: FragmentMatchDayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDayBinding.inflate(inflater)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MatchDayFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}