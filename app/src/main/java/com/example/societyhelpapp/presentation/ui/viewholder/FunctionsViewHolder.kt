package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.databinding.ItemTopicBinding
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel

class FunctionsViewHolder(private val binding: ItemTopicBinding): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: FunctionModel) -> Unit)? = null

    fun bind(item: FunctionModel) = binding.apply {
        itItemTitle.text = item.functionName
        itItemCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
    }
}