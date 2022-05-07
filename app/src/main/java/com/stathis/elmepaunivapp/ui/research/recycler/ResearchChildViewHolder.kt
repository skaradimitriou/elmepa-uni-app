package com.stathis.elmepaunivapp.ui.research.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.research.model.ResearchItem

class ResearchChildViewHolder(val binding : ViewDataBinding,val callback : ElmepaClickListener) : AbstractViewHolder(binding) {
    override fun present(data: LocalModel) {
        when(data){
            is ResearchItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}