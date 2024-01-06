package com.example.societyhelpapp.presentation.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.databinding.ItemConstitutionBinding

class ConstitutionViewHolder(private val binding: ItemConstitutionBinding): RecyclerView.ViewHolder(binding.root) {

    var clickCallback: ((item: Subtitle) -> Unit)? = null

    fun bind(item: Subtitle) = binding.apply {
        icItemTitle.text = item.subtitle
        icItemCard.setOnClickListener {
            clickCallback?.invoke(item)
        }
    }
}