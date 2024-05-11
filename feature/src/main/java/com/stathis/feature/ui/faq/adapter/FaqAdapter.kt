package com.stathis.feature.ui.faq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.R
import com.stathis.feature.databinding.HolderEmptyViewBinding
import com.stathis.feature.databinding.HolderFaqItemBinding
import com.stathis.feature.databinding.HolderShimmerFaqBinding
import com.stathis.model.faq.Faq
import com.stathis.model.general.ShimmerItem

class FaqAdapter : ListAdapter<UiModel, FaqViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_faq_item -> {
                HolderFaqItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_faq -> {
                HolderShimmerFaqBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Faq -> R.layout.holder_faq_item
        is ShimmerItem -> R.layout.holder_shimmer_faq
        else -> R.layout.holder_empty_view
    }
}

class FaqViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Faq -> {
                val binding = (binding as HolderFaqItemBinding)
                binding.setVariable(BR.model, data)

                binding.questionTxtView.setOnClickListener {
                    data.isExpanded = !data.isExpanded
                    binding.model = data
                }
            }
        }
    }
}