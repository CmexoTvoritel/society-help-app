package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.databinding.ItemInfoBinding

class FunctionsInfoViewHolder(private val binding: ItemInfoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) = binding.apply {
        iiSubscriptionText.text = item
    }
}