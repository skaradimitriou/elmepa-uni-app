package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.EmptyModel
import com.stathis.elmepaunivapp.ui.main.department.model.Research
import com.stathis.elmepaunivapp.ui.main.department.model.VirtualTourModel

class DepartmentAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return DepartmentViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Research -> R.layout.holder_department_research_item
            is VirtualTourModel -> R.layout.holder_virtual_tour
            is EmptyModel -> R.layout.holder_empty_view
            else -> R.layout.holder_research_item_row
        }
    }
}