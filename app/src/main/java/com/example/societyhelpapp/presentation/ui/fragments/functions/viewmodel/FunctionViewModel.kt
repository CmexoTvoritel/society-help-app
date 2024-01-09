package com.example.societyhelpapp.presentation.ui.fragments.functions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.societyhelpapp.presentation.repository.FunctionRepository
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.FunctionAction
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.FunctionEvent
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class FunctionViewModel @Inject constructor(
    private val repository: FunctionRepository
): ViewModel() {

    private var _information = MutableSharedFlow<List<FunctionModel>>()
    val information = _information.asSharedFlow()

    private var _actions = MutableSharedFlow<FunctionAction>()
    val actions = _actions.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.infoFunctions.collect { info ->
                _information.emit(info)
            }
        }
    }

    private fun loadInformation() {
        repository.loadFunctions()
    }

    private fun goToInformation(item: FunctionModel) {
        repository.setOpenInformation(item = item)
        sendViewAction(action = FunctionAction.Navigate)
    }

    private fun searchInformation(query: String) {
        val answer = repository.searchInfo(query = query)
        sendViewAction(action = FunctionAction.ListChanged(topics = answer))
    }

    fun obtainEvent(event: FunctionEvent) {
        when(event) {
            is FunctionEvent.LoadInformation -> loadInformation()
            is FunctionEvent.SearchInformation -> searchInformation(query = event.query)
            is FunctionEvent.GoToInformation -> goToInformation(item = event.item)
        }
    }

    private fun sendViewAction(action: FunctionAction) = viewModelScope.launch {
        _actions.emit(action)
    }
}