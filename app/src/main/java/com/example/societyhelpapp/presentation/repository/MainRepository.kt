package com.example.societyhelpapp.presentation.repository

import android.content.Context
import android.util.Log
import com.example.societyhelpapp.data.model.Topic
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationType
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    @ActivityContext private val context: Context
) {

    private var _allInformation = MutableSharedFlow<List<Topic>>()
    val allInformation = _allInformation.asSharedFlow()

    private var _information: Topic? = null
    val information: Topic?
        get() = _information

    fun getListOfTopics() {
        CoroutineScope(Dispatchers.IO).launch {
            getListOfTopicsData()
        }
    }

    fun setOpenInformation(topic: Topic) {
        _information = topic
    }

    fun getInformation(): List<InformationModel> {
        return getInformationData()
    }

    private fun getInformationData(): List<InformationModel> {
        return if(information != null) {
            val answer = mutableListOf<InformationModel>()
            information!!.subtitles.forEach { subtitle ->
                answer.add(InformationModel(type = InformationType.SUBTITLE, text = subtitle.subtitle))
                if(subtitle.listOfDesc != null) {
                    subtitle.listOfDesc.forEach { description ->
                        answer.add(InformationModel(type = InformationType.DESCRIPTION, text = description.description))
                    }
                }
            }
            answer
        } else {
            emptyList()
        }
    }

    private suspend fun getListOfTopicsData() = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open("information.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val topics: List<Topic> = Json.decodeFromString<List<Topic>>(json)
            _allInformation.emit(topics)
        } catch (e: Exception) {
            Log.e("JSON error", "${e.message}")
        }
    }
}