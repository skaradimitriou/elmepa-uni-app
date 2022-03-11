package com.stathis.elmepaunivapp.ui.home.fragments.students.recycler

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.CarouselParent
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.NewStudentItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.UsefulLinksParent

class StudentViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is NewStudentItem -> {
                val adapter = UsefulLinksAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.model,data)
                binding.setVariable(BR.adapter,adapter)
            }

            is UsefulLinksParent -> {
                val adapter = UsefulLinksAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.model,data)
                binding.setVariable(BR.adapter,adapter)
            }

            is CarouselParent -> {
                val adapter = CarouselAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.model,data)
                binding.setVariable(BR.adapter,adapter)
            }
        }
    }
}
