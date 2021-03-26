package com.xkoranate.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.xkoranate.data.repositories.GameParametersRepository
import com.xkoranate.data.repositories.SetParticipantsRepository
import com.xkoranate.db.game.GameParameters
import com.xkoranate.db.participants.Participants

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val participantsRepository = SetParticipantsRepository(application)
    private val participants = participantsRepository.getAll()

    private val gameParametersRepository = GameParametersRepository(application)
    private val game = gameParametersRepository.getGame()

//    var eventName = ""
//    private var allowDraws = false
//    private var minSkill = 0
//    private var maxSkill = 0


    // Function calls for game
    fun getGame(): LiveData<List<GameParameters>> {
        return game
    }

    fun insertGame(gameParameters: GameParameters) {
        gameParametersRepository.insertGame(gameParameters)
    }

    fun updateGame(gameParameters: GameParameters) {
        gameParametersRepository.updateGame(gameParameters)
    }

    fun deleteGame() {
        gameParametersRepository.deleteGame()
    }


    // Function calls for participants
    fun getAllParticipants(): LiveData<List<Participants>> {
        return participants
    }

    fun insert(participants: Participants) {
        participantsRepository.insert(participants)
    }

    fun delete() {
        participantsRepository.deleteAll()
    }


//    private var viewModelJob = Job()
//
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

}