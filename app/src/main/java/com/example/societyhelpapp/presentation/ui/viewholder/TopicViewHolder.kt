package com.example.societyhelpapp.presentation.ui.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.databinding.ItemTopicBinding
import com.example.societyhelpapp.presentation.ui.fragments.main.model.topic.TopicUI

class TopicViewHolder(private val binding: ItemTopicBinding): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: TopicUI) -> Unit)? = null

    fun bind(item: TopicUI) = binding.apply {
        itItemTitle.text = item.title
        itItemCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
    }
}