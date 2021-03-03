package com.xkoranate.ui.viewmodels.participants

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.db.participants.ParticipantsDao

class SetParticipantsViewModelFactory(
    private val dataSource: ParticipantsDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetParticipantsViewModel::class.java)) {
            return SetParticipantsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}