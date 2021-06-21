package com.stathis.elmepaunivapp.ui.main.professors.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel

class ProfessorsAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ProfessorsViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.professor_item_row,parent,false)
        return ProfessorsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ProfessorsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}