package com.stathis.dashboard.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.dashboard.BR
import com.stathis.dashboard.R
import com.stathis.dashboard.databinding.HolderCardItemBinding
import com.stathis.dashboard.databinding.HolderDashboardOptionBinding
import com.stathis.model.UiModel
import com.stathis.model.general.GeneralCardModel
import com.stathis.model.home.DashboardOption

class DashboardAdapter(
    private val callback: DashboardCallback
) : ListAdapter<UiModel, DashboardViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_card_item -> {
                HolderCardItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_dashboard_option -> {
                HolderDashboardOptionBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return DashboardViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is GeneralCardModel -> R.layout.holder_card_item
        is DashboardOption -> R.layout.holder_dashboard_option
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class DashboardViewHolder(
    private val binding: ViewDataBinding,
    private val callback: DashboardCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is GeneralCardModel -> {
                binding.setVariable(BR.model, data)
            }

            is DashboardOption -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface DashboardCallback {
    fun onDashboardItemTap(model: DashboardOption)
}