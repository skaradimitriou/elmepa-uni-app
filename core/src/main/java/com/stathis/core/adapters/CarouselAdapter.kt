package com.stathis.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.BR
import com.stathis.core.R
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.databinding.HolderCarouselItemBinding
import com.stathis.core.databinding.HolderEmptyViewBinding
import com.stathis.core.databinding.HolderShimmerCarouselItemBinding
import com.stathis.model.UiModel
import com.stathis.model.general.ShimmerItem
import com.stathis.model.general.carousel.CarouselItem

class CarouselAdapter(
    private val callback: CarouselCallback
) : ListAdapter<UiModel, CarouselViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_carousel_item -> {
                HolderCarouselItemBinding.inflate(inflater, parent, false)
            }

            R.layout.holder_shimmer_carousel_item -> {
                HolderShimmerCarouselItemBinding.inflate(inflater, parent, false)
            }

            else -> HolderEmptyViewBinding.inflate(inflater, parent, false)
        }
        return CarouselViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is CarouselItem -> R.layout.holder_carousel_item
        is ShimmerItem -> R.layout.holder_shimmer_carousel_item
        else -> R.layout.holder_empty_view
    }
}

class CarouselViewHolder(
    private val binding: ViewDataBinding,
    private val callback: CarouselCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is CarouselItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface CarouselCallback {
    fun onCarouselItemTap(model: CarouselItem)
}