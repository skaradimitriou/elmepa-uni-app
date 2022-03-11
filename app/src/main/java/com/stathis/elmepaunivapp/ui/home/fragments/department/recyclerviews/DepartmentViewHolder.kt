package com.stathis.elmepaunivapp.ui.home.fragments.department.recyclerviews

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.HorizontalDepartmentItem
import com.stathis.elmepaunivapp.ui.home.fragments.department.model.VerticalDepartmentItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.CarouselParent
import com.stathis.elmepaunivapp.ui.home.fragments.students.recycler.CarouselAdapter

class DepartmentViewHolder(val binding : ViewDataBinding,val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is HorizontalDepartmentItem -> {
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.adapter,adapter)
            }

            is VerticalDepartmentItem -> {
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.adapter,adapter)
            }

            is CarouselParent -> {
                val adapter = CarouselAdapter(callback)
                adapter.submitList(data.list)
                binding.setVariable(BR.item,data)
                binding.setVariable(BR.adapter,adapter)
            }
        }
    }
}