package com.example.societyhelpapp.presentation.ui.diffutil

import androidx.recyclerview.widget.DiffUtil

class FunctionInfoDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem == oldItem
    }
}