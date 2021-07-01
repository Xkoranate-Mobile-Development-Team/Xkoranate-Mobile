package com.xkoranate.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sports_table")
class Sport(
    var title: String?,
    var date: String?,
    var teamAImg: Bitmap?,
    var teamBImg: Bitmap?,
    var time: String?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
