package com.xkoranate.db.participants

import androidx.room.*

@Dao
interface ParticipantsDao {

    @Query("SELECT * FROM participants_table")
    fun getTasks()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(participants: Participants)

    @Update
    suspend fun update(participants: Participants)

    @Delete
    suspend fun delete(participants: Participants)
}