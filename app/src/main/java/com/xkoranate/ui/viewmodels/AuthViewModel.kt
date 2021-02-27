package com.xkoranate.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.xkoranate.data.repositories.AuthRepository
import com.xkoranate.other.AuthListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel : ViewModel() {

    private val authRepository by lazy {
        AuthRepository()
    }

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        authRepository.currentUser()
    }

    fun signUp() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = authRepository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun login(email: String, password: String) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = authRepository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}