package com.stathis.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {

    abstract fun bind(data: UiModel)
}