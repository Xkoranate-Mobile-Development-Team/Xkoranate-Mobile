package com.xkoranate.db.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_parameters_table")
data class GameParameters(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var eventName: String?,
    @ColumnInfo
    var allowDraws: Boolean?,
    @ColumnInfo
    var minSkill: Int?,
    @ColumnInfo
    var maxSkill: Int?
)
