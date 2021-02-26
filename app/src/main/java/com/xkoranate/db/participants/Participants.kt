package com.xkoranate.db.participants

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participants_table")
data class Participants(
    var participants: String?,
    var team: String?,
    var skill: Int?,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
)