package com.example.societyhelpapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.societyhelpapp.R
import com.example.societyhelpapp.data.model.constitution.Subtitle
import com.example.societyhelpapp.data.model.constitution.Topic
import com.example.societyhelpapp.databinding.ItemConstitutionBinding
import com.example.societyhelpapp.databinding.ItemConstitutionHeaderBinding
import com.example.societyhelpapp.presentation.ui.diffutil.ConstitutionDiffCallback
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.topic.SubtitleUI
import com.example.societyhelpapp.presentation.ui.viewholder.ConstitutionHeaderViewHolder
import com.example.societyhelpapp.presentation.ui.viewholder.ConstitutionViewHolder

class ConstitutionAdapter: ListAdapter<Any, RecyclerView.ViewHolder>(ConstitutionDiffCallback()) {

    var clickCallback: ((item: SubtitleUI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_constitution_header -> ConstitutionHeaderViewHolder(
                binding = ItemConstitutionHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_constitution -> ConstitutionViewHolder(
                binding = ItemConstitutionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("unknown type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ConstitutionHeaderViewHolder) {
            holder.bind(item = currentList[position] as Topic)
        }
        if(holder is ConstitutionViewHolder) {
            holder.bind(item = currentList[position] as SubtitleUI)
            holder.clickCallback = clickCallback
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(currentList[position] is Topic) {
            R.layout.item_constitution_header
        } else R.layout.item_constitution
    }
}