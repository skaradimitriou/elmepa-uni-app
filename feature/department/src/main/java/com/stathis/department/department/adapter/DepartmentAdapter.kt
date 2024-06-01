package com.stathis.department.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.adapters.CarouselAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.core.databinding.HolderViewpagerCarouselItemBinding
import com.stathis.department.BR
import com.stathis.department.R
import com.stathis.department.databinding.HolderDepMemberParentBinding
import com.stathis.department.databinding.HolderFieldofstudyParentBinding
import com.stathis.department.databinding.HolderProgrammeParentItemBinding
import com.stathis.department.databinding.HolderSocialParentBinding
import com.stathis.model.UiModel
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.department.Programme
import com.stathis.model.department.SocialItem
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.general.carousel.CarouselParent

class DepartmentAdapter(
    private val callback: DepartmentCallback
) : ListAdapter<UiModel, DepartmentViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            com.stathis.core.R.layout.holder_viewpager_carousel_item -> {
                HolderViewpagerCarouselItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_fieldofstudy_parent -> {
                HolderFieldofstudyParentBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_programme_parent_item -> {
                HolderProgrammeParentItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_dep_member_parent -> {
                HolderDepMemberParentBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_social_parent -> {
                HolderSocialParentBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }

        return DepartmentViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is CarouselParent -> com.stathis.core.R.layout.holder_viewpager_carousel_item
        is DepartmentProgrammeItem -> R.layout.holder_programme_parent_item
        is FieldOfStudyParent -> R.layout.holder_fieldofstudy_parent
        is DepartmentPersonnelItem -> R.layout.holder_dep_member_parent
        is DepartmentSocialItem -> R.layout.holder_social_parent
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class DepartmentViewHolder(
    private val binding: ViewDataBinding,
    private val callback: DepartmentCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is CarouselParent -> {
                val adapter = CarouselAdapter { selectedItem ->
                    callback.onCarouselItemTap(selectedItem)
                }

                adapter.submitList(data.carouselItems)
                binding.setVariable(BR.adapter, adapter)
            }

            is DepartmentProgrammeItem -> {
                val adapter = ProgrammeAdapter { selectedItem ->
                    callback.onProgrammeItemTap(selectedItem)
                }

                adapter.submitList(data.programmes)
                binding.setVariable(BR.adapter, adapter)
            }

            is FieldOfStudyParent -> {
                val adapter = FieldOfStudyAdapter()
                adapter.submitList(data.syllabusItems)
                binding.setVariable(BR.adapter, adapter)
            }

            is DepartmentPersonnelItem -> {
                val adapter = DepMembersAdapter()
                adapter.submitList(data.personnel)
                binding.setVariable(BR.adapter, adapter)
            }

            is DepartmentSocialItem -> {
                val adapter = SocialItemAdapter { selectedItem ->
                    callback.onSocialItemTap(selectedItem)
                }
                adapter.submitList(data.socialItems)
                binding.setVariable(BR.adapter, adapter)
            }
        }
    }
}

interface DepartmentCallback {
    fun onCarouselItemTap(model: CarouselItem)
    fun onProgrammeItemTap(model: Programme)
    fun onSocialItemTap(model: SocialItem)
}