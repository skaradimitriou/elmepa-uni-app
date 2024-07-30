package com.stathis.support.ui.applicationforms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.general.ShimmerItem
import com.stathis.model.support.ApplicationFormItem
import com.stathis.support.BR
import com.stathis.support.R
import com.stathis.support.databinding.HolderApplicationFormBinding
import com.stathis.support.databinding.HolderShimmerApplicationFormItemBinding

class ApplicationFormsAdapter(
    private val callback: ApplicationFormsCallback
) : ListAdapter<UiModel, ApplicationFormsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationFormsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_application_form -> {
                HolderApplicationFormBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_application_form_item -> {
                HolderShimmerApplicationFormItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }

        return ApplicationFormsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ApplicationFormsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ApplicationFormItem -> R.layout.holder_application_form
        is ShimmerItem -> R.layout.holder_shimmer_application_form_item
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class ApplicationFormsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ApplicationFormsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is ApplicationFormItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface ApplicationFormsCallback {
    fun onApplicationFormClick(model: ApplicationFormItem)
}