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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.db.participants.Participants
import com.xkoranate.ui.activities.MainActivity
import com.xkoranate.ui.adapters.SetParticipantsAdapter
import com.xkoranate.ui.viewmodels.participants.SetParticipantsViewModel


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null
    lateinit var viewModel: SetParticipantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetParticipantsBinding.inflate(inflater)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(SetParticipantsViewModel::class.java)

        binding?.lifecycleOwner = this.viewLifecycleOwner

        refreshList()

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

        binding?.deleteAllFab?.setOnClickListener {
            viewModel.delete()
            refreshList()
        }

        binding?.fab?.setOnClickListener {

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
            val dialogView = layoutInflater.inflate(R.layout.dialog_set_participants, null)
            dialog.setView(dialogView)

            val setParticipant = dialogView?.findViewById<EditText>(R.id.setParticipantsET)
            val teamName = dialogView?.findViewById<EditText>(R.id.teamNameET)
            val skillLevel = dialogView?.findViewById<EditText>(R.id.skillLevelET)
            dialog.setTitle("Set participants")
            dialog.setNegativeButton("Cancel") { _, _ ->

            }
            dialog.setPositiveButton("Set") { _, _ ->


                if (setParticipant?.text?.isNotEmpty()!! && teamName?.text?.isNotEmpty()!!) {
                    val participants = Participants(
                        participants = setParticipant.text.toString(),
                        team = teamName.text.toString(),
                        skill = skillLevel?.text?.toString()?.toInt()
                    )

                    viewModel.insert(participants)

                    refreshList()
                    binding?.deleteAllFab?.visibility = View.VISIBLE

                } else {
                    Toast.makeText(activity, "Pls fill in all fields", Toast.LENGTH_SHORT)
                        .show()

                    refreshList()
                }
            }

            dialog.show()

        }


        return binding?.root
    }

    private fun refreshList() {

        binding?.lifecycleOwner?.let {
            viewModel.getAllParticipants().observe(it, Observer { list ->

                binding?.recyclerView?.adapter = SetParticipantsAdapter(list)

                if (list.isEmpty()) {
                    binding?.addImage?.visibility = View.VISIBLE
                } else {
                    binding?.addImage?.visibility = View.GONE
                    binding?.deleteAllFab?.visibility = View.VISIBLE
                }
            })
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}