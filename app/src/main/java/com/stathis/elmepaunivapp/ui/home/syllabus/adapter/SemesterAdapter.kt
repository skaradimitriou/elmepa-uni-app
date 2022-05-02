package com.stathis.elmepaunivapp.ui.home.syllabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.databinding.HolderEmptyItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderSemesterItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderSemesterShimmerItemBinding
import com.stathis.elmepaunivapp.model.ShimmerModel
import com.stathis.elmepaunivapp.model.syllabus.Semester

class SemesterAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, SemesterViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val view = when(viewType){
            R.layout.holder_semester_item_row -> HolderSemesterItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_semester_shimmer_item -> HolderSemesterShimmerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return SemesterViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is Semester -> R.layout.holder_semester_item_row
        is ShimmerModel -> R.layout.holder_semester_shimmer_item
        else -> R.layout.holder_empty_item_row
    }
}