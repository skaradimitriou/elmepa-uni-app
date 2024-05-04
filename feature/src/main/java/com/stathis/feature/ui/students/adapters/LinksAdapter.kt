package com.stathis.feature.ui.students.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.databinding.HolderLinkItemBinding
import com.stathis.model.students.Link

class LinksAdapter(
    private val callback: LinksCallback
) : ListAdapter<UiModel, LinksViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderLinkItemBinding.inflate(inflater, parent, false)
        return LinksViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LinksViewHolder(
    private val binding: ViewDataBinding,
    private val callback: LinksCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Link -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface LinksCallback {
    fun onLinkTap(model: Link)
}