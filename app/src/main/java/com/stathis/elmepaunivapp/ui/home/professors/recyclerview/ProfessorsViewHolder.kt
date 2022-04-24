package com.stathis.elmepaunivapp.ui.home.professors.recyclerview

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.professors.model.Professor

class ProfessorsViewHolder(val binding : ViewDataBinding, val  callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is Professor -> {
                binding.setVariable(BR.professor,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}