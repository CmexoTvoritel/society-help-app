package com.example.societyhelpapp.presentation.ui.fragments.functions.model

import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel

sealed interface FunctionAction {
    data object Navigate: FunctionAction
    data class ListChanged(val topics: List<FunctionModel>): FunctionAction
}

sealed interface FunctionEvent {
    data object LoadInformation: FunctionEvent
    data class GoToInformation(val item: FunctionModel): FunctionEvent
    data class SearchInformation(val query: String): FunctionEvent
}