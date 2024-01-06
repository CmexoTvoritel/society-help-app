package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.societyhelpapp.databinding.ItemInfoBinding
import com.example.societyhelpapp.presentation.ui.diffutil.ConstitutionInfoDiffCallback
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import com.example.societyhelpapp.presentation.ui.viewholder.InformationViewHolder

class ConstitutionInfoAdapter: ListAdapter<InformationModel, InformationViewHolder>(ConstitutionInfoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        return InformationViewHolder(binding = ItemInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        holder.bind(item = currentList[position])
    }
}