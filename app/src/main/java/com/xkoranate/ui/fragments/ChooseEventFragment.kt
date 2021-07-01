package com.xkoranate.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xkoranate.databinding.FragmentChooseEventBinding
import com.xkoranate.databinding.ItemSportBinding
import com.xkoranate.ui.viewmodels.SharedViewModel
import java.io.IOException

private const val SPORTS_FOLDER = "sports"
var sportSelected = ""

class ChooseEventFragment : Fragment() {

    private var binding: FragmentChooseEventBinding? = null

    private var eventAdapter: ChooseEventAdapter? = null
    private var sports: Array<String>? = null
    private var tempSports: Array<String>? = null
    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseEventBinding.inflate(inflater)

        viewModel = ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)
            .create(SharedViewModel::class.java)

        binding?.continueButtonChooseEvent?.setOnClickListener {

            if (sportSelected == "") {
                Toast.makeText(this.requireContext(), "Pls choose a sport", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Navigation.findNavController(it)
                    .navigate(
                        ChooseEventFragmentDirections
                            .actionChooseEventFragment2ToIndividualEventFragment2(sportSelected)
                    )
            }
        }

        sports = requireActivity().assets.list(SPORTS_FOLDER)

        loadEvents(SPORTS_FOLDER)

        var path = SPORTS_FOLDER
        eventAdapter = ChooseEventAdapter(
            this.requireActivity().application,
            sports?.toCollection(ArrayList()) as ArrayList<String>
        ) {
            path += "/$it"
            if (loadEvents(path)) {
                eventAdapter?.sports = sports?.toCollection(ArrayList()) as ArrayList<String>
                eventAdapter?.notifyDataSetChanged()
            } else {
                path = SPORTS_FOLDER
            }
        }

        binding?.recyclerSports?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = eventAdapter
        }

        return binding?.root
    }

    private fun loadEvents(path: String): Boolean {
        try {
            tempSports = requireActivity().assets.list(path)!!
            for (file in tempSports as Array<String>) {
                return if (!file.contains(".xml")) {
                    sports = tempSports
                    true
                } else {
                    false
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    class ChooseEventAdapter(
        val context: Application,
        var sports: ArrayList<String>,
        private val onSportsClicked: (String) -> Unit
    ) :
        RecyclerView.Adapter<ChooseEventAdapter.SportViewHolder>() {

        private lateinit var sport: String

        private var selectedPosition = RecyclerView.NO_POSITION

        inner class SportViewHolder(val binding: ItemSportBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(sport: String) {
                binding.tvSport.text = sport

                itemView.setOnClickListener {
                    onSportsClicked.invoke(sport)
                    notifyItemChanged(selectedPosition)
                    selectedPosition = layoutPosition
                    notifyItemChanged(selectedPosition)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
            return SportViewHolder(ItemSportBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
            sport = sports[position]
            holder.bind(sport)
            holder.binding.background.isSelected = (selectedPosition == position)

            sportSelected = sport
        }

        override fun getItemCount(): Int {
            return sports.size
        }
    }
}
