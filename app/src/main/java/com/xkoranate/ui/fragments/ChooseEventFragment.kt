package com.xkoranate.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xkoranate.R
import com.xkoranate.databinding.FragmentChooseEventBinding
import com.xkoranate.databinding.ItemSportBinding
import java.io.IOException
import kotlin.collections.ArrayList

private const val SPORTS_FOLDER = "sports"

class ChooseEventFragment : Fragment() {

    private var binding: FragmentChooseEventBinding? = null

    private var eventAdapter: ChooseEventAdapter? = null
    private var sports: Array<String>? = null
    private var tempSports: Array<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseEventBinding.inflate(inflater)

        // The values of the variables below would be updated from the spinner values
        var isIndividual = true
        var isRoundRobin = false

        binding?.continueButtonChooseEvent?.setOnClickListener {
            if (isIndividual) {
                Navigation.findNavController(it)
                    .navigate(R.id.action_chooseEventFragment_to_individualEventFragment)
            }
            if (isRoundRobin) {
                Navigation.findNavController(it)
                    .navigate(R.id.action_chooseEventFragment_to_roundRobinFragment)
            }
        }

        sports  = requireActivity().assets.list(SPORTS_FOLDER)

        loadEvents(SPORTS_FOLDER)

        var path = SPORTS_FOLDER
        eventAdapter = ChooseEventAdapter(sports?.toCollection(ArrayList()) as ArrayList<String>) {
            path += "/$it"
            if (loadEvents(path)) {
                eventAdapter?.sports = sports?.toCollection(ArrayList()) as ArrayList<String>
                eventAdapter?.notifyDataSetChanged()
            } else {
                path = SPORTS_FOLDER
            }
        }

        binding?.recyclerSports?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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

    class ChooseEventAdapter(var sports: ArrayList<String>, private val onSportsClicked: (String) -> Unit) :
        RecyclerView.Adapter<ChooseEventAdapter.SportViewHolder>() {

        private lateinit var sport: String

        private var selectedPosition = RecyclerView.NO_POSITION

        inner class SportViewHolder(val binding: ItemSportBinding) : RecyclerView.ViewHolder(binding.root) {

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
            sport = sports.get(position)
            holder.bind(sport)
            holder.binding.background.isSelected = (selectedPosition == position)
        }

        override fun getItemCount(): Int {
            return sports.size
        }

    }
}