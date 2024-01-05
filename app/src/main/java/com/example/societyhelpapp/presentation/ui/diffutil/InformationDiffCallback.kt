package com.example.societyhelpapp.presentation.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel

class InformationDiffCallback: DiffUtil.ItemCallback<InformationModel>() {
    override fun areItemsTheSame(oldItem: InformationModel, newItem: InformationModel): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: InformationModel, newItem: InformationModel): Boolean {
        return (newItem.text == oldItem.text
                && newItem.type == oldItem.type)
    }
}