package com.xkoranate.db.participants

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Participants::class], version = 1)
abstract class ParticipantsDatabase : RoomDatabase() {

    abstract fun participantsDao(): ParticipantsDao

}