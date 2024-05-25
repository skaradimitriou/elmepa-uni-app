package com.stathis.feature.ui.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.core.base.BaseDiffUtil
import com.stathis.core.base.BaseViewHolder
import com.stathis.model.UiModel
import com.stathis.feature.BR
import com.stathis.feature.databinding.HolderContactItemBinding
import com.stathis.model.contact.ContactItem

class ContactAdapter(
    val callback: ContactCallback
) : ListAdapter<UiModel, ContactViewHolder>(BaseDiffUtil<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HolderContactItemBinding.inflate(inflater, parent, false)
        return ContactViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ContactViewHolder(
    val binding: ViewDataBinding,
    val callback: ContactCallback
) : BaseViewHolder(binding) {

    override fun bind(data: UiModel) {
        when (data) {
            is ContactItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface ContactCallback {
    fun onContactItemClick(model: ContactItem)
}