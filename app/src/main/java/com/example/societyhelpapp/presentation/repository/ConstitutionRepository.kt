package com.example.societyhelpapp.presentation.repository

import android.content.Context
import com.example.societyhelpapp.data.model.constitution.Topic
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@ActivityScoped
class ConstitutionRepository @Inject constructor(
    @ActivityContext private val context: Context
) {

    private var _constitutions = MutableSharedFlow<List<Topic>>()
    val constitutions = _constitutions.asSharedFlow()
}