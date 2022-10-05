package com.ei.android.numbergame.domain.entity

data class GameResult(
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val cuntOfQuestions:Int,
    val gameSettings: GameSettings
) {
}