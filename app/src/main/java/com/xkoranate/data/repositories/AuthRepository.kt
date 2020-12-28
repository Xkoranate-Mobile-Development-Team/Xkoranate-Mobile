package com.xkoranate.data.repositories

import com.xkoranate.data.firebase.FirebaseSource

class AuthRepository {

    private val firebase: FirebaseSource = FirebaseSource()


    fun register(email: String, password: String) = firebase.register(email, password)

    fun login(email: String, password: String) = firebase.login(email, password)

    fun currentUser() = firebase.currentUser()

    fun logOut() = firebase.logout()
}