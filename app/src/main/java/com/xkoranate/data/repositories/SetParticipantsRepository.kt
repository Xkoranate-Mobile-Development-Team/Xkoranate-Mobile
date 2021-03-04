package com.xkoranate.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.xkoranate.db.participants.Participants
import com.xkoranate.db.participants.ParticipantsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetParticipantsRepository(application: Application) {

    val db = ParticipantsDatabase.getInstance(application)
    private val dao = db.participantsDao
    val participants = dao.getParticipants()

    fun getAll(): LiveData<List<Participants>> {
        return participants
    }

    suspend fun insert(participants: Participants) {
        withContext(Dispatchers.IO) {
            dao.insert(participants)
        }
    }

    fun deleteAll() {
        dao.clear()
    }

}