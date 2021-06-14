package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.Schedule
import com.stathis.elmepaunivapp.ui.main.students.model.StudentItem

class StudentAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, StudentViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return StudentViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is StudentItem -> R.layout.holder_research_item_row
            is Schedule -> R.layout.holder_schedule_item_row
            else -> R.layout.holder_empty_view
        }
    }
}