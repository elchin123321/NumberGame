package com.ei.android.numbergame.domain.repository

import com.ei.android.numbergame.domain.entity.GameSettings
import com.ei.android.numbergame.domain.entity.Level
import com.ei.android.numbergame.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}