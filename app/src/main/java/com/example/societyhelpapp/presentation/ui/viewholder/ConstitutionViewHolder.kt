package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.databinding.ItemConstitutionBinding
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.SubtitleUI

class ConstitutionViewHolder(private val binding: ItemConstitutionBinding): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: SubtitleUI) -> Unit)? = null

    fun bind(item: SubtitleUI) = binding.apply {
        icItemTitle.text = item.subtitle
        icItemCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
    }
}