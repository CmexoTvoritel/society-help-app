package com.example.societyhelpapp.presentation.ui.fragments.main.model

import com.example.societyhelpapp.data.model.Topic

sealed interface UIAction {
    data object NavigateInformation: UIAction
}

sealed interface UIEvent {
    data object LoadInformation: UIEvent
    data class OpenInformation(val topic: Topic): UIEvent
}