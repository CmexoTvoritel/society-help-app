package com.example.societyhelpapp.presentation.ui.fragments.main.model

import com.example.societyhelpapp.data.model.main.Topic

sealed interface UIAction {
    data object NavigateInformation: UIAction
    data class ListChanged(val topics: List<Topic>): UIAction
}

sealed interface UIEvent {
    data object LoadInformation: UIEvent
    data class OpenInformation(val topic: Topic): UIEvent
    data class FilterChanged(val query: String): UIEvent
}