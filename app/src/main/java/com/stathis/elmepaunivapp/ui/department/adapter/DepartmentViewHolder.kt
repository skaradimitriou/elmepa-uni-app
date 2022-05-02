package com.stathis.elmepaunivapp.ui.department.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.department.model.HorizontalDepartmentItem
import com.stathis.elmepaunivapp.ui.department.model.VerticalDepartmentItem
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselParent
import com.stathis.elmepaunivapp.ui.home.students.recycler.CarouselAdapter

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