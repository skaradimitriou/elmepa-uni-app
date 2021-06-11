package com.stathis.elmepaunivapp.ui.research.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.listeners.new.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel

class ResearchAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ResearchViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_research_item_row,parent,false)
        return ResearchViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}