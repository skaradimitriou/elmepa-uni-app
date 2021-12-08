package com.stathis.elmepaunivapp.ui.syllabus.recyclerview

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester

class SemesterViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is Semester -> {
                binding.setVariable(BR.semester,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}
