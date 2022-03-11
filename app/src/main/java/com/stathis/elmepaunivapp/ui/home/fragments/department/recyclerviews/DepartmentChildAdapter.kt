package com.stathis.elmepaunivapp.ui.home.fragments.department.recyclerviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.*
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.DepMember
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.Programme
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.SocialChannel

class DepartmentChildAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, DepartmentChildViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentChildViewHolder {
        val view = when (viewType) {
            R.layout.holder_field_of_study_item -> HolderFieldOfStudyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.programmes_item_row -> ProgrammesItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.social_item_row -> SocialItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.dep_members_item_row -> DepMembersItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return DepartmentChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is FieldOfStudy -> R.layout.holder_field_of_study_item
        is Programme -> R.layout.programmes_item_row
        is SocialChannel -> R.layout.social_item_row
        is DepMember -> R.layout.dep_members_item_row
        else -> R.layout.holder_empty_item_row
    }
}