package com.example.societyhelpapp.presentation.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.data.model.constitution.Topic

class ConstitutionDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if(oldItem is Topic && newItem is Topic) {
            oldItem.title == newItem.title && oldItem.subtitles == newItem.subtitles
        } else if (oldItem is Subtitle && newItem is Subtitle)
            oldItem.subtitle == newItem.subtitle && oldItem.listOfDesc == newItem.listOfDesc
        else
            false
    }
}