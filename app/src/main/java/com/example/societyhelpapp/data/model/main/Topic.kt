package com.example.societyhelpapp.data.model.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val title: String,
    @SerialName("listOfSubtitle")
    val subtitles: List<Subtitle>
)