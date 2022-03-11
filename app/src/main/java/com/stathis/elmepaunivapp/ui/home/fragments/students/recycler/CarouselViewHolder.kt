package com.stathis.elmepaunivapp.ui.home.fragments.students.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.CarouselItem

class CarouselViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is CarouselItem -> {
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}