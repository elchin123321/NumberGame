package com.ei.android.numbergame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ei.android.numbergame.R
import com.ei.android.numbergame.domain.entity.GameResult

@BindingAdapter("isWinner")
fun bindRequiredAnswers(imageView: ImageView, winner: Boolean) {
    if (winner)
        imageView.setImageResource(R.drawable.ic_smile)
    else
        imageView.setImageResource(R.drawable.ic_sad)
}
@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercent")
fun bindScorePercent(textView: TextView, gameResult: GameResult) {
    val answer = if (gameResult.countOfQuestions == 0)
        0
    else
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        answer
    )
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView,enough:Boolean){
    textView.setTextColor(getColorByState(textView.context,enough))
}
@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar,enough:Boolean){
    progressBar.progressTintList = ColorStateList.valueOf( getColorByState(progressBar.context,enough))
}
@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView,number:Int){
    textView.text = number.toString()
}
@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView,clickListener:(Int)->Unit){
    textView.setOnClickListener{
        clickListener(textView.text.toString().toInt())
    }
}
private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return  ContextCompat.getColor(context, colorResId)

}