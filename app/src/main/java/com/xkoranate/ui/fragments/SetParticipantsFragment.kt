package com.xkoranate.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.db.participants.ParticipantsDao
import com.xkoranate.db.participants.ParticipantsDatabase
import com.xkoranate.ui.activities.MainActivity


class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null
    lateinit var participantsDatabase: ParticipantsDatabase
    lateinit var participantsDao: ParticipantsDao
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetParticipantsBinding.inflate(inflater)

        binding?.btnContinue?.setOnClickListener {

            // Todo: Add the condition for the presence of data in participants db
            if (false) {

                Toast.makeText(
                    activity,
                    "Pls add participants and skills constraints",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                mainActivity.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
                    .putBoolean("isThereGame", true).apply()
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

            val dialog = MaterialAlertDialogBuilder(this.requireContext())
                .setTitle("Set participants")
                .setView(R.layout.dialog_set_participants)
                .setNegativeButton("Cancel") { dialog, which ->
                    // Respond to cancel button
                }
                .setPositiveButton("Set") { dialog, which ->
                    // Respond to set button
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