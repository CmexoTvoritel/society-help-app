package com.example.societyhelpapp.presentation.ui.fragments.main.model.topic

import android.text.Spannable
import android.text.SpannableString

data class TopicUI(
    val title: Spannable,
    val subtitles: List<SubtitleUI>
) {
    constructor(title: String, subtitles: List<SubtitleUI>) : this(SpannableString(title), subtitles)
}