package com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic

import android.text.Spannable
import android.text.SpannableString

data class FunctionModel (
    val functionName: Spannable,
    val listOfDesc: List<FunctionDesc>
) {
    constructor(
        functionName: String,
        listOfDesc: List<FunctionDesc>
    ): this(functionName = SpannableString(functionName), listOfDesc = listOfDesc)
}