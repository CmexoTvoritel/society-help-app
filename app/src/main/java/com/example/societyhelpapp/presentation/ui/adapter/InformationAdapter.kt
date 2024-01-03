package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.R
import com.example.societyhelpapp.databinding.ItemInfoBinding
import com.example.societyhelpapp.databinding.ItemInfoDescBinding
import com.example.societyhelpapp.presentation.ui.diffutil.InformationDiffCallback
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import com.example.societyhelpapp.presentation.ui.viewholder.InformationDescViewHolder
import com.example.societyhelpapp.presentation.ui.viewholder.InformationViewHolder

class InformationAdapter: ListAdapter<InformationModel, RecyclerView.ViewHolder>(InformationDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_info ->  InformationViewHolder(
                binding = ItemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_info_desc -> InformationDescViewHolder(
                binding = ItemInfoDescBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is InformationViewHolder) {
            holder.bind(item = currentList[position])
        }
        else if(holder is InformationDescViewHolder) {
            holder.bind(item = currentList[position])
        }
    }
}