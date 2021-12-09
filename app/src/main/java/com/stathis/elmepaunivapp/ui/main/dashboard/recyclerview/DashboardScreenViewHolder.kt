package com.stathis.elmepaunivapp.ui.main.dashboard.recyclerview

import com.stathis.elmepaunivapp.BR
import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.dashboard.model.DashboardOption

class DashboardScreenViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is DashboardOption -> {
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}
