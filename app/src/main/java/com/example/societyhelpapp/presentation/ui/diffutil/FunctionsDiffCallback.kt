package com.example.societyhelpapp.presentation.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel

class FunctionsDiffCallback: DiffUtil.ItemCallback<FunctionModel>() {
    override fun areItemsTheSame(oldItem: FunctionModel, newItem: FunctionModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FunctionModel, newItem: FunctionModel): Boolean {
        return false
    }
}