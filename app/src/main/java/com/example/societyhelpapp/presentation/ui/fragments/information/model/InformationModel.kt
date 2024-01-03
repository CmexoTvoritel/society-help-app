package com.example.societyhelpapp.presentation.ui.fragments.information.model

data class InformationModel (
    val type: InformationType,
    val text: String,
)

enum class InformationType {
    SUBTITLE,
    DESCRIPTION
}