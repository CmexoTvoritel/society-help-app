package com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.model

import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel

sealed interface ConstitutionInfoAction {
    data class SendInformation(val information: List<InformationModel>): ConstitutionInfoAction
    data class LoadTitle(val title: String): ConstitutionInfoAction
}

sealed interface ConstitutionInfoEvent {
    data object LoadInformation: ConstitutionInfoEvent
}