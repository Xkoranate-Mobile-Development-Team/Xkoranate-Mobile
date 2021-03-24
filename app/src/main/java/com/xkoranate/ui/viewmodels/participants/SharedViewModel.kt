package com.xkoranate.ui.viewmodels.participants

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.xkoranate.data.repositories.SetParticipantsRepository
import com.xkoranate.db.participants.Participants

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SetParticipantsRepository(application)
    private val participants = repository.getAll()

    private var eventName = ""
    private var allowDraws = false
    private var minSkill = 0
    private var maxSkill = 0


    fun eventName(event: String) {
        eventName = event
    }

    fun allowDraws(draw: Boolean) {
        allowDraws = draw
    }

    fun minSkill(skill: Int) {
        minSkill = skill
    }

    fun maxSkill(skill: Int) {
        maxSkill = skill
    }


    fun getAllParticipants(): LiveData<List<Participants>> {
        return participants
    }

    fun insert(participants: Participants) {
        repository.insert(participants)
    }

    fun delete() {
        repository.deleteAll()
    }


//    private var viewModelJob = Job()
//
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

}