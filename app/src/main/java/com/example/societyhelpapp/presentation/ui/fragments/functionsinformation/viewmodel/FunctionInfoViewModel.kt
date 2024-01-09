package com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.societyhelpapp.presentation.repository.FunctionRepository
import com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.model.FunctionInfoAction
import com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.model.FunctionInfoEvent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class FunctionInfoViewModel @Inject constructor(
    private val repository: FunctionRepository
): ViewModel() {

    private var _actions = MutableSharedFlow<FunctionInfoAction>()
    val actions = _actions.asSharedFlow()

    private fun loadInformation() {
        sendViewAction(action = FunctionInfoAction.SetTitleInformation(title = repository.getOpenTitle()))
        sendViewAction(action = FunctionInfoAction.SetDescriptionInformation(descriptions = repository.getDescriptions()))
    }

    fun obtainEvent(event: FunctionInfoEvent) {
        when(event) {
            is FunctionInfoEvent.LoadInformation -> loadInformation()
        }
    }

    private fun sendViewAction(action: FunctionInfoAction) = viewModelScope.launch {
        _actions.emit(action)
    }
}