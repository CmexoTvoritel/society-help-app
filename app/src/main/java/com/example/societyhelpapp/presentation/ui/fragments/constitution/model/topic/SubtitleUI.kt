package com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic

import android.text.Spannable
import android.text.SpannableString


data class SubtitleUI(
    val subtitle: Spannable,
    val listOfDesc: List<DescriptionUI>? = null
) {
    constructor(
        subtitle: String,
        listOfDesc: List<DescriptionUI>?
    ) : this(subtitle = SpannableString(subtitle), listOfDesc = listOfDesc)
}
