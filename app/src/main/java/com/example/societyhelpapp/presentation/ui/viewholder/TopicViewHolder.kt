package com.example.societyhelpapp.presentation.ui.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.data.model.main.Topic
import com.example.societyhelpapp.databinding.ItemTopicBinding

class TopicViewHolder(private val binding: ItemTopicBinding): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: Topic) -> Unit)? = null

    fun bind(item: Topic) = binding.apply {
        itItemTitle.text = item.title
        itItemCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
    }
}