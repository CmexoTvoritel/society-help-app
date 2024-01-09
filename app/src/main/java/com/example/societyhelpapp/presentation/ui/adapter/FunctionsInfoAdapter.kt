package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.societyhelpapp.databinding.ItemInfoBinding
import com.example.societyhelpapp.presentation.ui.diffutil.FunctionInfoDiffCallback
import com.example.societyhelpapp.presentation.ui.viewholder.FunctionsInfoViewHolder

class FunctionsInfoAdapter: ListAdapter<String, FunctionsInfoViewHolder>(FunctionInfoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionsInfoViewHolder {
        return FunctionsInfoViewHolder(binding =
            ItemInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FunctionsInfoViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }
}