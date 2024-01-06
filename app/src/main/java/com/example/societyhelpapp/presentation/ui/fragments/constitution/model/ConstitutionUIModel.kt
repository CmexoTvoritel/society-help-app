package com.example.societyhelpapp.presentation.ui.fragments.constitution.model

import com.example.societyhelpapp.data.model.constitution.Subtitle

sealed interface ConstitutionAction {
    data object Navigate: ConstitutionAction
    data class ListChanged(val constitutions: List<Any>): ConstitutionAction
}

sealed interface ConstitutionEvent {
    data object LoadInformation: ConstitutionEvent
    data class ItemClicked(val infoConstitution: Subtitle): ConstitutionEvent
    data class FilterChanged(val query: String): ConstitutionEvent
}