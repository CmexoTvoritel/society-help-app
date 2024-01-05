package com.example.societyhelpapp.presentation.ui.fragments.information.model

sealed interface InformationUIAction {
    data class LoadInformation(val information: List<InformationModel>): InformationUIAction
    data class LoadTitle(val title: String): InformationUIAction
}

sealed interface InformationUIEvent {
    data object LoadInformation: InformationUIEvent
}