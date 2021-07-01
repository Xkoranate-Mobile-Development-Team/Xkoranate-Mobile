package com.xkoranate.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.xkoranate.db.participants.Participants
import com.xkoranate.db.participants.ParticipantsDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetParticipantsRepository(application: Application) {

    val db = ParticipantsDatabase.getInstance(application)
    private val dao = db.participantsDao
    val participants = dao.getParticipants()

    fun getAll(): LiveData<List<Participants>> {
        return participants
    }

    fun insert(participants: Participants) {
        GlobalScope.launch {
            dao.insert(participants)
        }
    }

    fun deleteAll() {
        GlobalScope.launch {
            dao.clear()
        }
    }
}
