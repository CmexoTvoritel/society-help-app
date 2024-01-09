package com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.model

sealed interface FunctionInfoAction {
    data class SetTitleInformation(val title: String): FunctionInfoAction
    data class SetDescriptionInformation(val descriptions: List<String>): FunctionInfoAction
}

sealed interface FunctionInfoEvent {
    data object LoadInformation: FunctionInfoEvent
}