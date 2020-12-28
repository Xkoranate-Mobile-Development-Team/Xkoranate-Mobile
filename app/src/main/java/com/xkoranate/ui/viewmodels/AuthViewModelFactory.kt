package com.xkoranate.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xkoranate.data.repositories.AuthRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel() as T
    }

}