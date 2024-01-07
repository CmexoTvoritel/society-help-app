package com.example.societyhelpapp.presentation.ui.fragments.main.model

import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.TopicUI

sealed interface UIAction {
    data object NavigateInformation: UIAction
    data class ListChanged(val topics: List<TopicUI>): UIAction
}

sealed interface UIEvent {
    data object LoadInformation: UIEvent
    data class OpenInformation(val topic: TopicUI): UIEvent
    data class FilterChanged(val query: String): UIEvent
}