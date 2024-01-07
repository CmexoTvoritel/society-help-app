package com.example.societyhelpapp.presentation.ui.fragments.constitution.model

import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.SubtitleUI

sealed interface ConstitutionAction {
    data object Navigate: ConstitutionAction
    data class ListChanged(val constitutions: List<Any>): ConstitutionAction
}

sealed interface ConstitutionEvent {
    data object LoadInformation: ConstitutionEvent
    data class ItemClicked(val infoConstitution: SubtitleUI): ConstitutionEvent
    data class FilterChanged(val query: String): ConstitutionEvent
}