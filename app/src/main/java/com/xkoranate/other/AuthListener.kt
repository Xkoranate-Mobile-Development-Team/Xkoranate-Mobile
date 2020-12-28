package com.xkoranate.other

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)


}