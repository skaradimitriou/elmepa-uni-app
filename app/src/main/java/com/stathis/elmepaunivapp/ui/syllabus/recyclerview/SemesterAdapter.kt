package com.stathis.elmepaunivapp.ui.syllabus.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderSemesterItemRowBinding
import com.stathis.elmepaunivapp.databinding.ProfessorItemRowBinding

class SemesterAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, SemesterViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val view = HolderSemesterItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SemesterViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}