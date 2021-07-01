package com.xkoranate.db.participants

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParticipantsDao {

    @Query("SELECT * FROM participants_table")
    fun getParticipants(): LiveData<List<Participants>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(participants: Participants)

    @Query("DELETE FROM participants_table")
    fun clear()
}
