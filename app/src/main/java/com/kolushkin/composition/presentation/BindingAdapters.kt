package com.kolushkin.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kolushkin.composition.R
import com.kolushkin.composition.domain.entity.GameResult

//GameFinishedFragment
@BindingAdapter("emoji_result")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

@BindingAdapter("required_answers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("score_answers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@BindingAdapter("required_percentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("score_percentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getSmileResId(winner: Boolean): Int {
    return if(winner){
        R.drawable.ic_smile
    }else{
        R.drawable.ic_sad
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult){
    if(countOfQuestions == 0){
        0
    }else{
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

//GameFragment

interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@BindingAdapter("progress_bar")
fun bindProgressBar(progressBar: ProgressBar, progress: Int) {
    progressBar.setProgress(progress, true)
}

@BindingAdapter("enough_count")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enough_percent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("number_as_text")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("on_option_click_listener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener{
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}
