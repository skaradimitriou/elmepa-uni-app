package com.stathis.elmepaunivapp.ui.department.adapter

import com.stathis.elmepaunivapp.BR
import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.department.model.DepMember
import com.stathis.elmepaunivapp.ui.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.department.model.Programme
import com.stathis.elmepaunivapp.ui.department.model.SocialChannel

class DepartmentChildViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is FieldOfStudy -> bindElements(data)
            is Programme -> bindElements(data)
            is SocialChannel -> bindElements(data)
            is DepMember -> bindElements(data)
        }
    }

    private fun bindElements(data : LocalModel){
        binding.setVariable(BR.model,data)
        binding.setVariable(BR.callback,callback)
    }
}
