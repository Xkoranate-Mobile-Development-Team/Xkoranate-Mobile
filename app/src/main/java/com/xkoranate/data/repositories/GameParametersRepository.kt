package com.xkoranate.data.repositories

import android.app.Application
import com.xkoranate.db.game.GameParameterDatabase
import com.xkoranate.db.game.GameParameters
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameParametersRepository(application: Application) {

    private val db = GameParameterDatabase.getInstance(application)
    private val dao = db.gameParametersDao
    private val game = dao.getGame()

    fun getGame(): List<GameParameters> {
        return game
    }

    fun insertGame(gameParameters: GameParameters) {
        GlobalScope.launch {
            dao.insert(gameParameters)
        }
    }

    fun deleteGame() {
        GlobalScope.launch {
            dao.deleteGame()
        }
    }

}