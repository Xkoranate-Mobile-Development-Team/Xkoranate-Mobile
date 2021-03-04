package com.xkoranate.ui.viewmodels.participants

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SetParticipantsViewModelFactory(application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val application = application

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        return SetParticipantsViewModel(application) as T
    }
}