package com.xkoranate.ui.viewmodels

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xkoranate.data.repositories.GameParametersRepository
import com.xkoranate.data.repositories.SetParticipantsRepository
import com.xkoranate.db.game.GameParameters
import com.xkoranate.db.participants.Participants

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val participantsRepository = SetParticipantsRepository(application)
    private val participants = participantsRepository.getAll()

    private val gameParametersRepository = GameParametersRepository(application)
    private val game = gameParametersRepository.getGame()

    private lateinit var timer: CountDownTimer
    val matchTimeLeft: MutableLiveData<String?> = MutableLiveData()

//    var eventName = ""
//    private var allowDraws = false
//    private var minSkill = 0
//    private var maxSkill = 0

    private var timeLeft = 10000L


//    @JvmName("setMatchTimeLeft1")
//    fun setMatchTimeLeft(time: String) {
//        this.matchTimeLeft = time
//
//
//    }

    fun startTimer() {

        timer = object : CountDownTimer(timeLeft, 1000) {

            override fun onTick(p0: Long) {
                timeLeft = p0 / 1000
                matchTimeLeft.value = timeLeft.toString()
            }

            override fun onFinish() {

                saveGame()
            }
        }

        timer.start()
    }

    // Function calls for match day
    fun saveGame() {
        // Todo: Save game
    }

    // Function calls for game
    fun getGame(): LiveData<List<GameParameters>> {
        return game
    }
    fun insertGame(gameParameters: GameParameters) {
        gameParametersRepository.insertGame(gameParameters)
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