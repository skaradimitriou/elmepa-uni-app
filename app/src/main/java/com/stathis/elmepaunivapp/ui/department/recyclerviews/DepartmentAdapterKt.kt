package com.stathis.elmepaunivapp.ui.department.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.department.model.EmptyModelKt
import com.stathis.elmepaunivapp.ui.department.model.ResearchKt
import com.stathis.elmepaunivapp.ui.department.model.VirtualTourModel

class DepartmentAdapterKt(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentViewHolderKt>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolderKt {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return DepartmentViewHolderKt(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolderKt, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is ResearchKt -> R.layout.holder_department_research_item
            is VirtualTourModel -> R.layout.holder_virtual_tour
            is EmptyModelKt -> R.layout.holder_empty_view
            else -> R.layout.holder_research_item_row
        }
    }
}