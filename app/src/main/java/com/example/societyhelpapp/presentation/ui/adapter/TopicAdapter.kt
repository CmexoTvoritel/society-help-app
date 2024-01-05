package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.data.model.main.Topic
import com.example.societyhelpapp.databinding.ItemTopicBinding
import com.example.societyhelpapp.presentation.ui.diffutil.TopicDiffCallback
import com.example.societyhelpapp.presentation.ui.viewholder.TopicViewHolder

class TopicAdapter: ListAdapter<Topic, RecyclerView.ViewHolder>(TopicDiffCallback()) {

    var clickCallback: ((item: Topic) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopicViewHolder(
            binding = ItemTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is TopicViewHolder) {
            holder.bind(item = currentList[position])
            holder.clickCallback = clickCallback
        }
    }
}