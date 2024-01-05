package com.example.societyhelpapp.presentation.ui.fragments.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.societyhelpapp.data.model.main.Topic
import com.example.societyhelpapp.presentation.repository.MainRepository
import com.example.societyhelpapp.presentation.ui.fragments.main.model.UIAction
import com.example.societyhelpapp.presentation.ui.fragments.main.model.UIEvent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private var _allInformation = MutableSharedFlow<List<Topic>>()
    val allInformation = _allInformation.asSharedFlow()

    private var _actions = MutableSharedFlow<UIAction>()
    val actions = _actions.asSharedFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.allInformation.collect {
                _allInformation.emit(it)
            }
        }
    }

    private fun loadInformation() {
        repository.getListOfTopics()
    }

    private fun openInformation(topic: Topic) {
        repository.setOpenInformation(topic = topic)
        sendViewAction(action = UIAction.NavigateInformation)
    }

    private fun filterChanged(query: String) {
        val answer = repository.filterInformation(query = query)
        sendViewAction(action = UIAction.ListChanged(topics = answer))
    }

    fun obtainEvent(viewEvent: UIEvent) {
        when(viewEvent) {
            is UIEvent.LoadInformation -> loadInformation()
            is UIEvent.OpenInformation -> openInformation(topic = viewEvent.topic)
            is UIEvent.FilterChanged -> filterChanged(query = viewEvent.query)
        }
    }

    private fun sendViewAction(action: UIAction) {
        CoroutineScope(Dispatchers.IO).launch {
            _actions.emit(action)
        }
    }
}