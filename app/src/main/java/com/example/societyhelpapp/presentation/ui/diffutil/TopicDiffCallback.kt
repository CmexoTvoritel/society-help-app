package com.example.societyhelpapp.presentation.ui.diffutil

import android.graphics.Color
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import androidx.recyclerview.widget.DiffUtil
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.TopicUI

class TopicDiffCallback: DiffUtil.ItemCallback<TopicUI>() {
    override fun areItemsTheSame(oldItem: TopicUI, newItem: TopicUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TopicUI, newItem: TopicUI): Boolean {
        return false
    }
}