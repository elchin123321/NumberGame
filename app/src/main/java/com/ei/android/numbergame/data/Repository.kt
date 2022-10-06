package com.ei.android.numbergame.data

import com.ei.android.numbergame.domain.entity.GameSettings
import com.ei.android.numbergame.domain.entity.Level
import com.ei.android.numbergame.domain.entity.Question
import com.ei.android.numbergame.domain.repository.GameRepository
import kotlin.math.min
import kotlin.random.Random

object Repository : GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = MIN_ANSWER_VALUE
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(10, 3, 50, 8)
            }
            Level.EASY -> {
                GameSettings(10, 10, 50, 60)
            }
            Level.NORMAL -> {
                GameSettings(30, 20, 70, 60)
            }
            Level.HARD -> {
                GameSettings(50, 20, 90, 60)
            }
        }
    }
}