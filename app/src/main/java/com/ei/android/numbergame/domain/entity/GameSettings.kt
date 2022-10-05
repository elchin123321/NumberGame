package com.ei.android.numbergame.domain.entity

data class GameSettings(
    val maxSumValue:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswer:Int,
    val gameTime:Int
) {
}