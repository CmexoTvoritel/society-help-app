package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.data.model.constitution.Topic
import com.example.societyhelpapp.databinding.ItemConstitutionHeaderBinding

class ConstitutionHeaderViewHolder(
    private val binding: ItemConstitutionHeaderBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Topic) = binding.apply {
        ichText.text = item.title
    }
}