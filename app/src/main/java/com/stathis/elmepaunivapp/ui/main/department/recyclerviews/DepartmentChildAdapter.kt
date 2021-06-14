package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.*

class DepartmentChildAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentChildViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return DepartmentChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is FieldOfStudy -> R.layout.field_item_row
            is Programme -> R.layout.programmes_item_row
            is SocialChannel -> R.layout.social_item_row
            is DepMember -> R.layout.dep_members_item_row
            else -> R.layout.holder_empty_view
        }
    }
}