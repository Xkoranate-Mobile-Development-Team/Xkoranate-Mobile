package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.xkoranate.R
import com.xkoranate.databinding.FragmentSetParticipantsBinding
import com.xkoranate.db.game.GameParameters
import com.xkoranate.db.participants.Participants
import com.xkoranate.ui.adapters.SetParticipantsAdapter
import com.xkoranate.ui.viewmodels.SharedViewModel

class SetParticipantsFragment : Fragment() {

    private var binding: FragmentSetParticipantsBinding? = null
    lateinit var viewModel: SharedViewModel
    private var minSkill: String? = null
    private var maxSkill: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetParticipantsBinding.inflate(inflater)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(SharedViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner

        refreshList()

        val args = arguments?.let { SetParticipantsFragmentArgs.fromBundle(it) }
        val sports = args?.sportSelected
        val allowDraws = args?.allowDraws

        binding?.btnContinue?.setOnClickListener {

            if (binding?.minSkillET?.text?.isEmpty()!! || binding?.maxSkillET?.text?.isEmpty()!! ||
                isParticipantsEmpty()
            ) {
                binding?.root?.let {
                    Snackbar.make(
                        it,
                        "Pls add participants and skills constraints", Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            } else {
                Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment)
                    .navigate(
                        SetParticipantsFragmentDirections.actionSetParticipantsFragment2ToHomeFragment()
                    )
            }

            val game = GameParameters(
                eventName = sports, allowDraws = allowDraws,
                minSkill = minSkill?.toInt(), maxSkill = maxSkill?.toInt()
            )

            viewModel.insertGame(game)
            viewModel.getGame()
        }

        binding?.minSkillET?.setOnClickListener {
            binding?.root?.let {
                Snackbar.make(
                    it,
                    getString(R.string.skills_instructions), Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

        binding?.maxSkillET?.setOnClickListener {
            binding?.root?.let {
                Snackbar.make(
                    it,
                    getString(R.string.skills_instructions), Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

        binding?.increaseSkill?.setOnClickListener {
            if (binding?.minSkillET?.text.toString() != "") {
                val newValue = binding?.minSkillET?.text.toString().toInt() + 1
                binding?.minSkillET?.setText(newValue.toString())
            } else {
                binding?.minSkillET?.setText("0")
            }

            minSkill = binding?.minSkillET?.text.toString()
        }

        binding?.decreaseSkill?.setOnClickListener {
            if (binding?.minSkillET?.text.toString() != "") {
                val newValue = binding?.minSkillET?.text.toString().toInt() - 1
                binding?.minSkillET?.setText(newValue.toString())
            } else {
                binding?.minSkillET?.setText("0")
            }

            minSkill = binding?.minSkillET?.text.toString()
        }

        binding?.increaseSkill2?.setOnClickListener {
            if (binding?.maxSkillET?.text.toString() != "") {
                val newValue = binding?.maxSkillET?.text.toString().toInt() + 1
                binding?.maxSkillET?.setText(newValue.toString())
            } else {
                binding?.maxSkillET?.setText("0")
            }

            maxSkill = binding?.maxSkillET?.text.toString()
        }

        binding?.decreaseSkill2?.setOnClickListener {
            if (binding?.maxSkillET?.text.toString() != "") {
                val newValue = binding?.maxSkillET?.text.toString().toInt() - 1
                binding?.maxSkillET?.setText(newValue.toString())
            } else {
                binding?.maxSkillET?.setText("0")
            }

            maxSkill = binding?.maxSkillET?.text.toString()
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
                // Closes the dialog itself
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
                    binding?.root?.let {
                        Snackbar.make(it, "Pls fill in all fields", Snackbar.LENGTH_LONG)
                            .show()
                    }
                    refreshList()
                }
            }

            dialog.show()
        }

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        binding?.root?.let {
            Snackbar.make(
                it,
                getString(R.string.skills_instructions), Snackbar.LENGTH_LONG
            )
                .show()
        }
    }

    private fun refreshList() {

        binding?.lifecycleOwner?.let {
            viewModel.getAllParticipants().observe(
                it,
                { list ->

                    binding?.recyclerView?.adapter = SetParticipantsAdapter(list)

                    if (list.isEmpty()) {
                        binding?.addImage?.visibility = View.VISIBLE
                    } else {
                        binding?.addImage?.visibility = View.GONE
                        binding?.deleteAllFab?.visibility = View.VISIBLE
                    }
                }
            )
        }
    }

    private fun isParticipantsEmpty(): Boolean {
        var isEmpty = true

        binding?.lifecycleOwner?.let {
            viewModel.getAllParticipants().observe(
                it,
                { list ->
                    isEmpty = list.isEmpty()
                }
            )
        }

        return isEmpty
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
