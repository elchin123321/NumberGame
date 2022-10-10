package com.ei.android.numbergame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult(
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val cuntOfQuestions:Int,
    val gameSettings: GameSettings
):Parcelable