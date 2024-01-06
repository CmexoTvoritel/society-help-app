package com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.societyhelpapp.presentation.repository.ConstitutionRepository
import com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.model.ConstitutionInfoAction
import com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.model.ConstitutionInfoEvent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class ConstitutionInfoViewModel @Inject constructor(
    private val repository: ConstitutionRepository
): ViewModel() {

    private var _actions = MutableSharedFlow<ConstitutionInfoAction>()
    val actions = _actions.asSharedFlow()

    private fun loadInformation() {
        val answer = repository.getInformation()
        sendViewAction(action = ConstitutionInfoAction.SendInformation(information = answer))
        if(repository.information != null)
            sendViewAction(action = ConstitutionInfoAction.LoadTitle(title = repository.information!!.subtitle))
    }

    fun obtainEvent(viewEvent: ConstitutionInfoEvent) {
        when(viewEvent) {
            is ConstitutionInfoEvent.LoadInformation -> loadInformation()
        }
    }

    private fun sendViewAction(action: ConstitutionInfoAction) {
        viewModelScope.launch {
            _actions.emit(action)
        }
    }
}