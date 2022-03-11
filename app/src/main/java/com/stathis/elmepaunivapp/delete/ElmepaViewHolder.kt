package com.stathis.elmepaunivapp.delete

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener

abstract class ElmepaViewHolder(itemView: View, callback: ElmepaClickListener? = null) :
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