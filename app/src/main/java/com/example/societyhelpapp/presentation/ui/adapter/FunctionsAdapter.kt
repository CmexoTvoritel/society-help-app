package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.societyhelpapp.databinding.ItemTopicBinding
import com.example.societyhelpapp.presentation.ui.diffutil.FunctionsDiffCallback
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel
import com.example.societyhelpapp.presentation.ui.viewholder.FunctionsViewHolder

class FunctionsAdapter: ListAdapter<FunctionModel, FunctionsViewHolder>(FunctionsDiffCallback()) {

    var clickCallback: ((item: FunctionModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionsViewHolder {
        return FunctionsViewHolder(
            binding = ItemTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FunctionsViewHolder, position: Int) {
        holder.bind(item = currentList[position])
        holder.clickCallback = clickCallback
    }
}