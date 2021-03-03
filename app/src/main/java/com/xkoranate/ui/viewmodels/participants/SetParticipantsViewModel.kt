package com.xkoranate.ui.viewmodels.participants

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.xkoranate.db.participants.Participants
import com.xkoranate.db.participants.ParticipantsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class SetParticipantsViewModel(val database: ParticipantsDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var participant = MutableLiveData<Participants?>()
    private val participants = database.getParticipants()

//    init {
//
//    }

//    private fun initialiseParticipant() {
//        uiScope.launch {
//            participant.value = getParticipantFromDatabase()
//        }
//    }

    fun getParticipants(): List<Participants> {
        return database.getParticipants()
    }

    suspend fun insert(participants: Participants) {
        withContext(Dispatchers.IO) {
            database.insert(participants)
        }
    }

    suspend fun onClear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }


}