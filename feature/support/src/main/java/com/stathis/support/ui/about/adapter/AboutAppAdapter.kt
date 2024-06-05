package com.stathis.support.ui.about.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.about.AboutAppCard
import com.stathis.model.about.AboutAppHeader
import com.stathis.support.BR
import com.stathis.support.R
import com.stathis.support.databinding.HolderAboutCardBinding
import com.stathis.support.databinding.HolderAboutInfoBinding

class AboutAppAdapter : ListAdapter<com.stathis.model.UiModel, AboutAppViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutAppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_about_info -> {
                HolderAboutInfoBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_about_card -> {
                HolderAboutCardBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return AboutAppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AboutAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is AboutAppHeader -> R.layout.holder_about_info
        is AboutAppCard -> R.layout.holder_about_card
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class AboutAppViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {

    override fun bind(data: com.stathis.model.UiModel) {
        when (data) {
            is AboutAppHeader, is AboutAppCard -> binding.setVariable(BR.model, data)
            else -> Unit
        }
    }
}