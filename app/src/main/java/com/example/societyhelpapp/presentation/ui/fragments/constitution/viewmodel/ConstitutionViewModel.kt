package com.example.societyhelpapp.presentation.ui.fragments.constitution.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.presentation.repository.ConstitutionRepository
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.ConstitutionAction
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.ConstitutionEvent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
class ConstitutionViewModel @Inject constructor(
    private val repository: ConstitutionRepository
): ViewModel() {

    private var _constitutionInfo = MutableSharedFlow<List<Any>>()
    val constitutionInfo = _constitutionInfo.asSharedFlow()

    private var _constitutionAction = MutableSharedFlow<ConstitutionAction>()
    val constitutionAction = _constitutionAction.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.constitutions.collect {
                _constitutionInfo.emit(it)
            }
        }
    }

    private fun loadInformation() {
        repository.getListOfTopics()
    }

    private fun itemClicked(subtitle: Subtitle) {
        repository.setOpenInformation(subtitle = subtitle)
        sendViewAction(action = ConstitutionAction.Navigate)
    }

    private fun filterChanged(query: String) {
        val answer = repository.filterInformation(query = query)
        sendViewAction(action = ConstitutionAction.ListChanged(constitutions = answer))
    }

    fun obtainEvent(event: ConstitutionEvent) {
        when(event) {
            is ConstitutionEvent.LoadInformation -> loadInformation()
            is ConstitutionEvent.ItemClicked -> itemClicked(subtitle = event.infoConstitution)
            is ConstitutionEvent.FilterChanged -> filterChanged(query = event.query)
        }
    }

    private fun sendViewAction(action: ConstitutionAction) {
        viewModelScope.launch {
            _constitutionAction.emit(action)
        }
    }
}