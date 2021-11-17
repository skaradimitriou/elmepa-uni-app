package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.util.Log
import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.NewDepartmentItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselParent
import com.stathis.elmepaunivapp.ui.main.students.recycler.CarouselAdapter
import kotlinx.android.synthetic.main.holder_parent_horizontal_nested_item.view.*
import kotlinx.android.synthetic.main.holder_viewpager_item.view.*

class DepartmentViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    val callback = callback

    override fun present(data: LocalModel) {
        when(data){
//            is DepMemberParent -> {
//                itemView.research_item_header.text = data.header
//                val adapter = DepartmentChildAdapter(callback)
//                adapter.submitList(data.list)
//                itemView.research_recycler.adapter = adapter
//            }
//
//            is FieldOfStudyParent -> {
//                itemView.research_item_header.text = data.header
//                val adapter = DepartmentChildAdapter(callback)
//                adapter.submitList(data.list)
//                itemView.research_recycler.adapter = adapter
//            }
//
//            is ProgrammeParent -> {
//                itemView.research_item_header.text = data.header
//                val adapter = DepartmentChildAdapter(callback)
//                adapter.submitList(data.list)
//                itemView.research_recycler.adapter = adapter
//            }
//
//            is SocialChannelParent -> {
//                itemView.research_item_header.text = data.header
//                val adapter = DepartmentChildAdapter(callback)
//                adapter.submitList(data.list)
//                itemView.research_recycler.adapter = adapter
//            }

            is NewDepartmentItem -> {
                Log.d("",data.title)
                itemView.nested_title.text = data.title
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                itemView.nested_recycler.adapter = adapter
            }

            is CarouselParent -> {
                val adapter = CarouselAdapter(callback)
                adapter.submitList(data.list)
                itemView.carousel_viewpager.adapter = adapter
            }
        }
    }
}