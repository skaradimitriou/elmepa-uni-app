package com.stathis.personnel.ui.depmemberdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.databinding.HolderEmptyViewBinding
import com.stathis.model.UiModel
import com.stathis.model.common.Header
import com.stathis.model.common.Link
import com.stathis.model.department.DepMember
import com.stathis.model.department.Skill
import com.stathis.personnel.BR
import com.stathis.personnel.R
import com.stathis.personnel.databinding.HolderPersonDetailsHeaderBinding
import com.stathis.personnel.databinding.HolderPersonDetailsLinkBinding
import com.stathis.personnel.databinding.HolderPersonDetailsSkillBinding
import com.stathis.personnel.databinding.HolderPersonDetailsTitleBinding

class DepDetailsAdapter(
    private val callback: DepDetailsCallback
) : ListAdapter<UiModel, PersonDetailsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_person_details_header -> {
                HolderPersonDetailsHeaderBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_person_details_skill -> {
                HolderPersonDetailsSkillBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_person_details_title -> {
                HolderPersonDetailsTitleBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_person_details_link -> {
                HolderPersonDetailsLinkBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return PersonDetailsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is DepMember -> R.layout.holder_person_details_header
        is Skill -> R.layout.holder_person_details_skill
        is Header -> R.layout.holder_person_details_title
        is Link -> R.layout.holder_person_details_link
        else -> com.stathis.common.R.layout.holder_empty_view
    }
}

class PersonDetailsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: DepDetailsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is DepMember, is Skill, is Header, is Link -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface DepDetailsCallback {
    fun onLinkTap(model: Link)
}