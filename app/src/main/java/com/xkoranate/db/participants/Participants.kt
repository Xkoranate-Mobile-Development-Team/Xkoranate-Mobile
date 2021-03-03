package com.xkoranate.db.participants

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participants_table")
data class Participants(
    @ColumnInfo
    var participants: String?,
    @ColumnInfo
    var team: String?,
    @ColumnInfo
    var skill: Int?,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
)