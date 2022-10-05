package com.ei.android.numbergame.domain.usecases

import com.ei.android.numbergame.domain.repository.GameRepository


class GenerateQuestion(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int) =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)


    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}