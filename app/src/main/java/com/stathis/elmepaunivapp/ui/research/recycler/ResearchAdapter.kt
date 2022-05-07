package com.stathis.elmepaunivapp.ui.research.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderResearchScreenItemBinding

class ResearchAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ResearchViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {
        val view = HolderResearchScreenItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResearchViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}