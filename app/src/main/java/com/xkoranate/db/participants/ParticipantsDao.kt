package com.xkoranate.db.participants

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParticipantsDao {

    @Query("SELECT * FROM participants_table" + "ORDER BY id DESC")
    fun getParticipants(): LiveData<List<Participants>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(participants: Participants)

    @Update
    suspend fun update(participants: Participants)

    @Query("DELETE FROM participants_table")
    fun clear()
}