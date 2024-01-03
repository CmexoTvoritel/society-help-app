package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.databinding.ItemInfoBinding
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel

class InformationViewHolder(private val binding: ItemInfoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InformationModel) = binding.apply {
        iiSubscriptionText.text = item.text
    }
}