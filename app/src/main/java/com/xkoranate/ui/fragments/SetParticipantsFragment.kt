package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.db.participants.Participants
import com.xkoranate.db.participants.ParticipantsDao
import com.xkoranate.db.participants.ParticipantsDatabase
import com.xkoranate.ui.activities.MainActivity
import com.xkoranate.ui.adapters.SetParticipantsAdapter
import com.xkoranate.ui.viewmodels.participants.SetParticipantsViewModel
import com.xkoranate.ui.viewmodels.participants.SetParticipantsViewModelFactory


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null
    lateinit var participantsDatabase: ParticipantsDatabase
    lateinit var participantsDao: ParticipantsDao
    lateinit var mainActivity: MainActivity
    lateinit var setParticipantsAdapter: SetParticipantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetParticipantsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dataSource = ParticipantsDatabase.getInstance(application).participantsDao
        val viewModelFactory = SetParticipantsViewModelFactory(dataSource, application)
        val setParticipantsViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            SetParticipantsViewModel::class.java
        )

        binding?.setParticipantsViewModel = setParticipantsViewModel

        binding?.lifecycleOwner = this

        setParticipantsAdapter = SetParticipantsAdapter(setParticipantsViewModel.getParticipants())
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = setParticipantsAdapter

        binding?.btnContinue?.setOnClickListener {

            // Todo: Add the condition for the presence of data in participants db
            if (false) {

                Toast.makeText(
                    activity,
                    "Pls add participants and skills constraints",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                startActivity(Intent(activity, MainActivity::class.java))
            }

        }

        binding?.increaseSkill?.setOnClickListener {
            if (binding?.minSkillET?.text.toString() != "") {
                val newValue = binding?.minSkillET?.text.toString().toInt() + 1
                binding?.minSkillET?.setText(newValue.toString())
            } else {
                binding?.minSkillET?.setText("0")
            }
        }

        binding?.decreaseSkill?.setOnClickListener {
            if (binding?.minSkillET?.text.toString() != "") {
                val newValue = binding?.minSkillET?.text.toString().toInt() - 1
                binding?.minSkillET?.setText(newValue.toString())
            } else {
                binding?.minSkillET?.setText("0")
            }
        }

        binding?.increaseSkill2?.setOnClickListener {
            if (binding?.maxSkillET?.text.toString() != "") {
                val newValue = binding?.maxSkillET?.text.toString().toInt() + 1
                binding?.maxSkillET?.setText(newValue.toString())
            } else {
                binding?.maxSkillET?.setText("0")
            }
        }

        binding?.decreaseSkill2?.setOnClickListener {
            if (binding?.maxSkillET?.text.toString() != "") {
                val newValue = binding?.maxSkillET?.text.toString().toInt() - 1
                binding?.maxSkillET?.setText(newValue.toString())
            } else {
                binding?.maxSkillET?.setText("0")
            }
        }

        binding?.fab?.setOnClickListener {

            val setParticipant = view?.findViewById<EditText>(R.id.setParticipantsET)
            val teamName = view?.findViewById<EditText>(R.id.teamNameET)
            val skillLevel = view?.findViewById<EditText>(R.id.skillLevelET)

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle("Set participants")
                .setView(R.layout.dialog_set_participants)
                .setNegativeButton("Cancel") { dialog, which ->
                    // Respond to cancel button
                }
                .setPositiveButton("Set") { dialog, which ->
                    // Respond to set button
                    val participant: Participants
                    participant.participants = setParticipant?.text.toString()
                    setParticipantsViewModel.insert(
                }

            dialog.show()

        }



        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}