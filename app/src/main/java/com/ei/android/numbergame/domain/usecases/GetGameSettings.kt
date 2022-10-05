package com.ei.android.numbergame.domain.usecases

import com.ei.android.numbergame.domain.entity.Level
import com.ei.android.numbergame.domain.repository.GameRepository


class GetGameSettings(private val repository: GameRepository) {

    operator fun invoke(level: Level) =
        repository.getGameSettings(level)
}