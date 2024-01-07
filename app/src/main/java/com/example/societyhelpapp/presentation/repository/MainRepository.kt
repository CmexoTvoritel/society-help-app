package com.example.societyhelpapp.presentation.repository

import android.content.Context
import android.util.Log
import com.example.societyhelpapp.data.model.main.Topic
import com.example.societyhelpapp.presentation.helpers.SpannableHelper
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationType
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.DescriptionUI
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.SubtitleUI
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.TopicUI
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
    @ActivityContext private val context: Context,
    private val spannableHelper: SpannableHelper
) {

    private var _allInformation = MutableSharedFlow<List<TopicUI>>()
    private var informationList = listOf<TopicUI>()
    val allInformation = _allInformation.asSharedFlow()

    private var _information: TopicUI? = null
    val information: TopicUI?
        get() = _information

    fun getListOfTopics() {
        CoroutineScope(Dispatchers.IO).launch {
            getListOfTopicsData()
        }
    }

    fun setOpenInformation(topic: TopicUI) {
        _information = topic
    }

    fun getInformation(): List<InformationModel> {
        return getInformationData()
    }

    fun filterInformation(query: String): List<TopicUI> {
        val filteredList = mutableListOf<TopicUI>()
        informationList = spannableHelper.highlightMain(query = query, itemsList = informationList)
        informationList.map {
            if(it.title.contains(query, true)) filteredList.add(it)
        }
        return filteredList
    }

    private fun getInformationData(): List<InformationModel> {
        return if(information != null) {
            val answer = mutableListOf<InformationModel>()
            var numOfSubtitle = 1
            information!!.subtitles.forEach { subtitle ->
                val textSubtitle = "$numOfSubtitle. " + subtitle.subtitle
                answer.add(InformationModel(type = InformationType.SUBTITLE, text = textSubtitle))
                if(subtitle.listOfDesc != null) {
                    subtitle.listOfDesc.forEach { description ->
                        answer.add(InformationModel(type = InformationType.DESCRIPTION, text = description.description))
                    }
                }
                numOfSubtitle++
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
            val needList = mapTopicToUI(topicList = topics)
            informationList = needList
            _allInformation.emit(needList)
        } catch (e: Exception) {
            Log.e("JSON error", "${e.message}")
        }
    }

    private fun mapTopicToUI(
        topicList: List<Topic>
    ): List<TopicUI> = topicList.map { topicToTopicUI(it) }

    private fun topicToTopicUI(topic: Topic): TopicUI {
        val subtitleUIList = topic.subtitles.map { subtitle ->
            SubtitleUI(
                subtitle.subtitle,
                subtitle.listOfDesc?.map { desc ->
                    DescriptionUI(desc.description)
                } ?: emptyList()
            )
        }
        return TopicUI(topic.title, subtitleUIList)
    }
}