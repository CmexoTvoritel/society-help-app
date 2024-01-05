package com.example.societyhelpapp.presentation.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.societyhelpapp.data.model.main.Topic

class TopicDiffCallback: DiffUtil.ItemCallback<Topic>() {
    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.subtitles[0] == newItem.subtitles[0] &&
                oldItem.title == newItem.title
    }
}