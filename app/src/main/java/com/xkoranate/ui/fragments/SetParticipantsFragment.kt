package com.xkoranate.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.db.participants.Participants
import com.xkoranate.ui.activities.MainActivity
import com.xkoranate.ui.adapters.SetParticipantsAdapter
import com.xkoranate.ui.viewmodels.participants.SetParticipantsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null
    lateinit var viewModel: SetParticipantsViewModel
    lateinit var setParticipantsAdapter: SetParticipantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetParticipantsBinding.inflate(inflater)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProviders.of(this).get(SetParticipantsViewModel::class.java)

        binding?.lifecycleOwner?.let {
            viewModel.getAllParticipants().observe(it, Observer<List<Participants>> {
                setParticipantsAdapter = SetParticipantsAdapter(it)
            })
        }

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
                    val participants = Participants(
                        participants = setParticipant?.text.toString(),
                        team = teamName?.text.toString(),
                        skill = skillLevel?.text?.toString()?.toInt()
                    )

                    GlobalScope.launch {
                        viewModel.insert(participants)
                    }
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