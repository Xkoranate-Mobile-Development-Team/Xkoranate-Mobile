package com.xkoranate.ui.viewmodels.participants

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.xkoranate.data.repositories.SetParticipantsRepository
import com.xkoranate.db.participants.Participants
import kotlinx.coroutines.Job

class SetParticipantsViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = SetParticipantsRepository(application)
    val participants = repository.participants

    fun getAllParticipants(): LiveData<List<Participants>> {
        return participants
    }

    suspend fun insert(participants: Participants) {
        repository.insert(participants)
    }


    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


//    fun getParticipants(): LiveData<List<Participants>> {
//        return getParticipants()
//    }

//    suspend fun insert(participants: Participants) {
//        withContext(Dispatchers.IO) {
//            insert(participants)
//        }
//    }

//    suspend fun onClear() {
//        withContext(Dispatchers.IO) {
//
//        }
//    }


}