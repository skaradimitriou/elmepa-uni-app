package com.stathis.elmepaunivapp.ui.announcements.recyclerviews

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement

class AnnouncementsViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is Announcement -> {
                binding.setVariable(BR.announcement,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}