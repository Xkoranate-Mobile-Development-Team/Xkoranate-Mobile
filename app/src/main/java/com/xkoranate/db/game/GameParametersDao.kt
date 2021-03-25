package com.xkoranate.db.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameParametersDao {

    @Query("SELECT * FROM game_parameters_table")
    fun getGame(): List<GameParameters>

    @Insert
    fun insert(gameParameters: GameParameters)

    @Query("DELETE FROM game_parameters_table")
    fun deleteGame()

}