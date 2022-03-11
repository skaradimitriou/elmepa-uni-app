package com.stathis.elmepaunivapp.ui.home.fragments.department.recyclerviews

import com.stathis.elmepaunivapp.BR
import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.DepMember
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.Programme
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.SocialChannel

class DepartmentChildViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is FieldOfStudy -> {
                binding.setVariable(BR.fieldOfStudy,data)
                binding.setVariable(BR.callback,callback)
            }

            is Programme -> {
                binding.setVariable(BR.programme,data)
                binding.setVariable(BR.callback,callback)
            }

            is SocialChannel -> {
                binding.setVariable(BR.social,data)
                binding.setVariable(BR.callback,callback)
            }

            is DepMember -> {
                binding.setVariable(BR.member,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}
