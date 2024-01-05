package com.example.societyhelpapp.presentation.ui.fragments.constitution.viewmodel

import androidx.lifecycle.ViewModel
import com.example.societyhelpapp.presentation.repository.ConstitutionRepository
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ConstitutionViewModel @Inject constructor(
    private val repository: ConstitutionRepository
): ViewModel() {


}