package com.stathis.core.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T : com.stathis.model.UiModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equalsContent(newItem)
    }
}