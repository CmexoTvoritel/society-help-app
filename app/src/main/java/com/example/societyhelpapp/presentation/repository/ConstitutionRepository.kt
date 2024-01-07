package com.example.societyhelpapp.presentation.repository

import android.content.Context
import android.util.Log
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.data.model.constitution.Topic
import com.example.societyhelpapp.presentation.helpers.SpannableHelper
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.DescriptionUI
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.SubtitleUI
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ActivityScoped
class ConstitutionRepository @Inject constructor(
    @ActivityContext private val context: Context,
    private val spannableHelper: SpannableHelper
) {

    private var _constitutions = MutableSharedFlow<List<Any>>()
    private var informationList = mutableListOf<Any>()
    val constitutions = _constitutions.asSharedFlow()

    private var _information: SubtitleUI? = null
    val information: SubtitleUI?
        get() = _information

    fun getListOfTopics() {
        CoroutineScope(Dispatchers.IO).launch {
            getListOfTopicsData()
        }
    }

    fun getInformation(): List<InformationModel> {
        return getInformationData()
    }

    private fun getInformationData(): List<InformationModel> {
        return if(_information != null) {
            val answer = mutableListOf<InformationModel>()
            _information!!.listOfDesc!!.forEach { desc ->
                answer.add(InformationModel(
                    type = InformationType.SUBTITLE,
                    text = desc.description
                ))
            }
            answer
        } else
            emptyList()
    }

    fun setOpenInformation(subtitle: SubtitleUI) {
        _information = subtitle
    }

    fun filterInformation(query: String): List<Any> {
        val filteredList = mutableListOf<Any>()
        informationList = spannableHelper.highlightConstitution(query = query, itemsList = informationList)
        informationList.map {
            if(it is SubtitleUI) {
                if(it.subtitle.contains(query, true)) filteredList.add(it)
            }
        }
        return filteredList
    }

    private suspend fun getListOfTopicsData() = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open("constitution.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val topics: List<Topic> = Json.decodeFromString<List<Topic>>(json)
            informationList = mutableListOf()
            topics.forEach { topic ->
                informationList.add(topic)
                topic.subtitles.forEach { subtitle ->
                    informationList.add(mapConstitutionToUI(subtitle = subtitle))
                }
            }
            _constitutions.emit(informationList)
        } catch (e: Exception) {
            Log.e("JSON error", "${e.message}")
        }
    }

    private fun mapConstitutionToUI(subtitle: Subtitle): SubtitleUI {
        val listOfDesc = subtitle.listOfDesc?.map { desc ->
            DescriptionUI(description = desc.description)
        } ?: emptyList()
        return SubtitleUI(subtitle.subtitle, listOfDesc)
    }

}