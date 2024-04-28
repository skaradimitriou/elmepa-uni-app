package com.stathis.feature.research.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.databinding.HolderResearchItemBinding
import com.stathis.model.research.ResearchItem

class ResearchChildAdapter(
    private val callback: ResearchCallback
) : ListAdapter<UiModel, ResearchChildViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderResearchItemBinding.inflate(inflater, parent, false)
        return ResearchChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ResearchChildViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ResearchChildViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ResearchCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is ResearchItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}