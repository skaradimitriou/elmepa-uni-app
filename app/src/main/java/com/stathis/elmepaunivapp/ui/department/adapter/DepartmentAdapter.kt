package com.stathis.elmepaunivapp.ui.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.*
import com.stathis.elmepaunivapp.ui.department.model.EmptyModel
import com.stathis.elmepaunivapp.ui.department.model.HorizontalDepartmentItem
import com.stathis.elmepaunivapp.ui.department.model.VerticalDepartmentItem
import com.stathis.elmepaunivapp.ui.students.model.CarouselParent

class DepartmentAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = when(viewType){
            R.layout.holder_viewpager_carousel_item -> HolderViewpagerCarouselItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_dept_horizontal_nested_item -> HolderDeptHorizontalNestedItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_dept_vertical_nested_item -> HolderDeptVerticalNestedItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_empty_view -> HolderEmptyViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return DepartmentViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is CarouselParent -> R.layout.holder_viewpager_carousel_item
        is HorizontalDepartmentItem -> R.layout.holder_dept_horizontal_nested_item
        is VerticalDepartmentItem -> R.layout.holder_dept_vertical_nested_item
        is EmptyModel -> R.layout.holder_empty_view
        else -> R.layout.holder_empty_view
    }
}