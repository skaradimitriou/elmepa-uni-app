package com.stathis.elmepaunivapp.ui.contact.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.contact.model.ContactItem

class ContactViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is ContactItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}