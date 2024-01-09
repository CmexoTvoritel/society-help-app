package com.example.societyhelpapp.data.model.functions

import kotlinx.serialization.Serializable

@Serializable
data class FunctionModel (
    val functionName: String,
    val listOfDesc: List<FunctionDesc>
)