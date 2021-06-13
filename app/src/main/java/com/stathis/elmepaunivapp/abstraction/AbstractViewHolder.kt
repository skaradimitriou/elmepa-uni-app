package com.stathis.elmepaunivapp.abstraction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel

abstract class AbstractViewHolder(itemView: View, callback: ElmepaClickListener? = null) :
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            callback?.onItemClick(it)
        }
    }

    fun bindData(data: LocalModel) {
        itemView.tag = data
        present(data)
    }

    abstract fun present(data: LocalModel)
}