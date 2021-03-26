package com.xkoranate.db.game

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "game_parameters_table")
data class GameParameters(
    @ColumnInfo
    var eventName: String?,
    @ColumnInfo
    var allowDraws: Boolean?,
    @ColumnInfo
    var minSkill: Int?,
    @ColumnInfo
    var maxSkill: Int?
)