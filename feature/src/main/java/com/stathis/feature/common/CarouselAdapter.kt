package com.stathis.feature.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.core.base.UiModel
import com.stathis.feature.BR
import com.stathis.feature.databinding.HolderCarouselItemBinding
import com.stathis.model.general.carousel.CarouselItem

class CarouselAdapter(
    private val callback: CarouselCallback
) : ListAdapter<UiModel, CarouselViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderCarouselItemBinding.inflate(inflater, parent, false)
        return CarouselViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
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