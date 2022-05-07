package com.stathis.elmepaunivapp.ui.research.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.databinding.HolderResearchItemBinding

class ResearchChildAdapter(val callback : ElmepaClickListener) : ListAdapter<LocalModel,ResearchChildViewHolder>(DiffItemClass<LocalModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchChildViewHolder {
        val view = HolderResearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResearchChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ResearchChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}