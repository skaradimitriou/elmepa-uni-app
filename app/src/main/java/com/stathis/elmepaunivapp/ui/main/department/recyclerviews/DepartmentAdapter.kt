package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.EmptyModel
import com.stathis.elmepaunivapp.ui.main.department.model.HorizontalDepartmentItem
import com.stathis.elmepaunivapp.ui.main.department.model.VerticalDepartmentItem
import com.stathis.elmepaunivapp.ui.main.students.model.CarouselParent

class DepartmentAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return DepartmentViewHolder(view, callback)

        //FIXME: Migrate to databinding pending
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is CarouselParent -> R.layout.holder_viewpager_item
        is HorizontalDepartmentItem -> R.layout.holder_parent_horizontal_nested_item
        is VerticalDepartmentItem -> R.layout.holder_parent_vertical_nested_item
        is EmptyModel -> R.layout.holder_empty_view
        else -> R.layout.holder_empty_view
    }
}