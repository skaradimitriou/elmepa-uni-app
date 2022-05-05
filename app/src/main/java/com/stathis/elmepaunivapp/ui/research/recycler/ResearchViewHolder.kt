package com.stathis.elmepaunivapp.ui.research.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.research.model.ResearchModel
import com.stathis.elmepaunivapp.ui.students.recycler.UsefulLinksAdapter

class ResearchViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is ResearchModel -> {
                val adapter = UsefulLinksAdapter(callback)
                adapter.submitList(data.researchItems)

                binding.setVariable(BR.model,data)
                binding.setVariable(BR.adapter,adapter)
            }
        }
    }
}
