package com.example.societyhelpapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Subtitle(
    val subtitle: String,
    val listOfDesc: List<Description>? = null
)
