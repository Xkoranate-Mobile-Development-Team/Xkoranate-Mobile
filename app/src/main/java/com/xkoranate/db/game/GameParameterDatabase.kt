package com.xkoranate.db.game

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameParameters::class], version = 1, exportSchema = false)
abstract class GameParameterDatabase : RoomDatabase() {
    abstract val gameParametersDao: GameParametersDao

    companion object {
        @Volatile
        private var INSTANCE: GameParameterDatabase? = null

        fun getInstance(context: Context): GameParameterDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GameParameterDatabase::class.java,
                        "game_parameters_table"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}