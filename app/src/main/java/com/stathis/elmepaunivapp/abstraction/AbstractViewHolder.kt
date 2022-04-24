package com.stathis.elmepaunivapp.abstraction

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder(itemView : ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {

    fun bindData(data: LocalModel) {
        present(data)
    }

    abstract fun present(data: LocalModel)
}