package com.example.societyhelpapp.presentation.ui.fragments.information.viewmodel

import androidx.lifecycle.ViewModel
import com.example.societyhelpapp.presentation.repository.MainRepository
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationUIAction
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationUIEvent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class InformationViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private var _actions = MutableSharedFlow<InformationUIAction>()
    val actions = _actions.asSharedFlow()

    private fun loadInformation() {
        val information = repository.getInformation()
        sendViewAction(action = InformationUIAction.LoadInformation(information = information))
        if(repository.information != null) {
            sendViewAction(action = InformationUIAction.LoadTitle(title = repository.information!!.title))
        }
    }

    fun obtainEvent(viewEvent: InformationUIEvent) {
        when(viewEvent) {
            is InformationUIEvent.LoadInformation -> loadInformation()
        }
    }

    private fun sendViewAction(action: InformationUIAction) {
        CoroutineScope(Dispatchers.IO).launch {
            _actions.emit(action)
        }
    }
}