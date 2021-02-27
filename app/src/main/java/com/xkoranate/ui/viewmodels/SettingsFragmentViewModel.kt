package com.xkoranate.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SettingsFragmentViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userId: String =
        savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
}