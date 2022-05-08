package com.stathis.elmepaunivapp.ui.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.databinding.HolderContactItemBinding

class ContactAdapter(val callback: ElmepaClickListener) : ListAdapter<LocalModel, ContactViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = HolderContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}