package com.ei.android.numbergame.domain.entity

data class Question(
    val sum:Int,
    val visibleNumber:Int,
    val options:List<Int>
)