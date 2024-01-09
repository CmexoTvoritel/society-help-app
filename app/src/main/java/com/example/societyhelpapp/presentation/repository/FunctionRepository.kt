package com.example.societyhelpapp.presentation.repository

import android.content.Context
import android.util.Log
import com.example.societyhelpapp.presentation.helpers.SpannableHelper
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionDesc
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ActivityScoped
class FunctionRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val spannableHelper: SpannableHelper
) {

    private var _infoFunctions = MutableSharedFlow<List<FunctionModel>>()
    private var infoList = listOf<FunctionModel>()
    val infoFunctions = _infoFunctions.asSharedFlow()

    private lateinit var openInformation: FunctionModel

    fun loadFunctions() {
        CoroutineScope(Dispatchers.IO).launch {
            loadFunctionsData()
        }
    }

    fun setOpenInformation(item: FunctionModel) {
        openInformation = item
    }

    fun getOpenTitle() = openInformation.functionName.toString()

    fun getDescriptions(): List<String> {
        val answerList = openInformation.listOfDesc.map { desc ->
            desc.description
        }
        return answerList
    }

    private suspend fun loadFunctionsData() = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open("functions.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val functions: List<com.example.societyhelpapp.data.model.functions.FunctionModel> = Json.decodeFromString(json)
            val needList = mapToFunctions(list = functions)
            infoList = needList
            _infoFunctions.emit(needList)
        }catch (e: Exception) {
            Log.e("JSON error", "${e.message}")
        }
    }

    fun searchInfo(query: String): List<FunctionModel> {
        val filteredInfo = mutableListOf<FunctionModel>()
        infoList = spannableHelper.highlightFunctions(query = query, itemsList = infoList)
        infoList.map {
            if(it.functionName.contains(query, true)) filteredInfo.add(it)
        }
        return filteredInfo
    }

    private fun mapToFunctions(
        list: List<com.example.societyhelpapp.data.model.functions.FunctionModel>
    ): List<FunctionModel> = list.map { functionToUI(item = it) }

    private fun functionToUI(item: com.example.societyhelpapp.data.model.functions.FunctionModel): FunctionModel {
        val descriptionList = item.listOfDesc.map { description ->
            FunctionDesc(
                "- ${description.description}"
            )
        }
        return FunctionModel(item.functionName, descriptionList)
    }
}