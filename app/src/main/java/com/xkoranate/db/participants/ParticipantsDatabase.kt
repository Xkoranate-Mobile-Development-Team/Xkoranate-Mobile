package com.xkoranate.db.participants

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Participants::class], version = 1)
abstract class ParticipantsDatabase : RoomDatabase() {

    abstract val participantsDao: ParticipantsDao

    companion object {
        @Volatile
        private var INSTANCE: ParticipantsDatabase? = null

        fun getInstance(context: Context): ParticipantsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParticipantsDatabase::class.java,
                        "participants_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}