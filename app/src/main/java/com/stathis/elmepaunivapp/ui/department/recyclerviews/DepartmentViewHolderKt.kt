package com.stathis.elmepaunivapp.ui.department.recyclerviews

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.department.model.*
import kotlinx.android.synthetic.main.holder_research_item_row.view.*

class DepartmentViewHolderKt(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    val callback = callback

    override fun present(data: LocalModel) {
        when(data){
            is DepMemberParent -> {
                itemView.research_item_header.text = data.header
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                itemView.research_recycler.adapter = adapter
            }

            is FieldOfStudyParent -> {
                itemView.research_item_header.text = data.header
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                itemView.research_recycler.adapter = adapter
            }

            is ProgrammeParent -> {
                itemView.research_item_header.text = data.header
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                itemView.research_recycler.adapter = adapter
            }

            is SocialChannelParent -> {
                itemView.research_item_header.text = data.header
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)
                itemView.research_recycler.adapter = adapter
            }
        }
    }
}