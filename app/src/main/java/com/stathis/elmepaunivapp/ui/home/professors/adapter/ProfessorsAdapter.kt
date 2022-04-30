package com.stathis.elmepaunivapp.ui.home.professors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderEmptyItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderShimmerProfessorItemBinding
import com.stathis.elmepaunivapp.databinding.ProfessorItemRowBinding
import com.stathis.elmepaunivapp.model.ShimmerModel
import com.stathis.elmepaunivapp.model.professor.Professor

class ProfessorsAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ProfessorsViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorsViewHolder {
        val view = when(viewType){
            R.layout.professor_item_row -> ProfessorItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_shimmer_professor_item -> HolderShimmerProfessorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return ProfessorsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ProfessorsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is Professor -> R.layout.professor_item_row
        is ShimmerModel -> R.layout.holder_shimmer_professor_item
        else -> R.layout.holder_empty_item_row
    }
}