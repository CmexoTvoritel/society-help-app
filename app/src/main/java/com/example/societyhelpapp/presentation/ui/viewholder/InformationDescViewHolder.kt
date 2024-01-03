package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.databinding.ItemInfoDescBinding
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel

class InformationDescViewHolder(private val binding: ItemInfoDescBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InformationModel) = binding.apply {
        iiDescriptionText.text = item.text
    }
}