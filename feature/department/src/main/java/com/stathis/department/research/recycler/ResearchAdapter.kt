package com.stathis.department.research.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.util.setupItemDecoration
import com.stathis.department.databinding.HolderResearchScreenItemBinding
import com.stathis.model.UiModel
import com.stathis.model.research.ResearchItem
import com.stathis.model.research.ResearchResponse

class ResearchAdapter(
    private val callback: ResearchCallback
) : ListAdapter<UiModel, ResearchViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderResearchScreenItemBinding.inflate(inflater, parent, false)
        return ResearchViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ResearchViewHolder(
    private val binding: HolderResearchScreenItemBinding,
    private val callback: ResearchCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is ResearchResponse -> {
                binding.model = data

                val itemAdapter = ResearchChildAdapter(callback)
                itemAdapter.submitList(data.researchItems)
                binding.adapter = itemAdapter

                binding.researchRecycler.setupItemDecoration(30, 20, 20)
            }
        }
    }
}

fun interface ResearchCallback {
    fun onResearchItemTap(item: ResearchItem)
}