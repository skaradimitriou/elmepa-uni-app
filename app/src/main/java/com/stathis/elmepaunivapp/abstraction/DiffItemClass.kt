package com.stathis.elmepaunivapp.abstraction

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.stathis.elmepaunivapp.model.LocalModel

class DiffItemClass<T : LocalModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equalsContent(newItem)
    }
}