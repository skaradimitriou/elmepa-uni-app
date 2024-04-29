package com.stathis.feature.ui.professors.adapter

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
import com.stathis.feature.databinding.HolderShimmerProfessorItemBinding
import com.stathis.feature.databinding.ProfessorItemRowBinding
import com.stathis.model.general.ShimmerItem
import com.stathis.model.professors.Professor

class ProfessorsAdapter(
    private val callback: ProfessorsCallback
) : ListAdapter<UiModel, ProfessorsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.professor_item_row -> {
                ProfessorItemRowBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_professor_item -> {
                HolderShimmerProfessorItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return ProfessorsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProfessorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Professor -> R.layout.professor_item_row
        is ShimmerItem -> R.layout.holder_shimmer_professor_item
        else -> R.layout.holder_empty_view
    }
}

class ProfessorsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ProfessorsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is Professor -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface ProfessorsCallback {
    fun onProfessorTap(model: Professor)
}