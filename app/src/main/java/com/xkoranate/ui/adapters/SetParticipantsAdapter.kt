package com.xkoranate.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xkoranate.R
import com.xkoranate.db.participants.Participants

class SetParticipantsAdapter(private val participants: List<Participants>) :
    RecyclerView.Adapter<SetParticipantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_child_set_participants, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.participant.text = participants[position].participants
        holder.team.text = participants[position].team
        holder.skill.text = participants[position].skill.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val participant: TextView = itemView.findViewById(R.id.rvParticipantTV)
        val team: TextView = itemView.findViewById(R.id.rvTeamTV)
        val skill: TextView = itemView.findViewById(R.id.rvSkillTV)
    }
}
