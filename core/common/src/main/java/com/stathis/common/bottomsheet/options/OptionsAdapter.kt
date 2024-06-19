package com.stathis.common.bottomsheet.options

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.common.base.BaseDiffUtil
import com.stathis.common.base.BaseViewHolder
import com.stathis.common.bottomsheet.BottomSheetOption
import com.stathis.common.databinding.HolderOptionItemBinding
import com.stathis.model.UiModel
import java.io.Serializable

class OptionsAdapter(
    private val callback: OptionsCallback
) : ListAdapter<BottomSheetOption, OptionsViewHolder>(BaseDiffUtil<BottomSheetOption>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderOptionItemBinding.inflate(inflater, parent, false)
        return OptionsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class OptionsViewHolder(
    private val binding: HolderOptionItemBinding,
    private val callback: OptionsCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is BottomSheetOption -> {
                binding.model = data
                binding.callback = callback
            }
        }
    }
}

fun interface OptionsCallback : Serializable {
    fun onOptionTap(model: BottomSheetOption)
}