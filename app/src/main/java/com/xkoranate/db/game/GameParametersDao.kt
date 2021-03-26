package com.xkoranate.db.game

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameParametersDao {

    @Query("SELECT * FROM game_parameters_table")
    fun getGame(): LiveData<List<GameParameters>>

    @Insert
    fun insert(gameParameters: GameParameters)

    @Update
    fun update(gameParameters: GameParameters)

    @Query("DELETE FROM game_parameters_table")
    fun deleteGame()

}