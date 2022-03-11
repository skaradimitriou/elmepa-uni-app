package com.stathis.elmepaunivapp.ui.home.fragments.students.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.SyllabusItem

class UsefulLinksViewHolder(val binding : ViewDataBinding, val callback: ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is LinkItem -> {
                binding.setVariable(BR.link,data)
                binding.setVariable(BR.callback,callback)
            }

            is SyllabusItem -> {
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.callback,callback)
            }

            is UsefulLinks -> {
                binding.setVariable(BR.usefulLink,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}
