package com.stathis.students.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.adapters.CarouselAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.core.databinding.HolderViewpagerCarouselItemBinding
import com.stathis.model.UiModel
import com.stathis.model.general.carousel.CarouselItem
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.students.Link
import com.stathis.model.students.LinkParent
import com.stathis.students.BR
import com.stathis.students.R
import com.stathis.students.databinding.HolderLinksParentItemBinding

class StudentsAdapter(
    private val callback: StudentsCallback
) : ListAdapter<UiModel, StudentsViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            com.stathis.core.R.layout.holder_viewpager_carousel_item -> {
                HolderViewpagerCarouselItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_links_parent_item -> {
                HolderLinksParentItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return StudentsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is CarouselParent -> com.stathis.core.R.layout.holder_viewpager_carousel_item
        is LinkParent -> R.layout.holder_links_parent_item
        else -> com.stathis.core.R.layout.holder_empty_view
    }
}

class StudentsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: StudentsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is CarouselParent -> {
                val adapter = CarouselAdapter { selectedItem ->
                    callback.onCarouselTap(selectedItem)
                }

                adapter.submitList(data.carouselItems)
                binding.setVariable(BR.adapter, adapter)
            }

            is LinkParent -> {
                val adapter = LinksAdapter { selectedLink ->
                    callback.onLinkTap(selectedLink)
                }
                adapter.submitList(data.links)
                binding.setVariable(BR.adapter, adapter)
            }
        }
    }
}

interface StudentsCallback {
    fun onCarouselTap(model: CarouselItem)
    fun onLinkTap(model: Link)
}