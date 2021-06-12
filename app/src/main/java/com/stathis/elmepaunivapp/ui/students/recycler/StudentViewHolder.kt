package com.stathis.elmepaunivapp.ui.students.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.students.model.ScheduleKt
import com.stathis.elmepaunivapp.ui.students.model.StudentItemKt
import kotlinx.android.synthetic.main.holder_research_item_row.view.*

class StudentViewHolder(itemView : View,callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    val callback = callback

    override fun present(data: LocalModel) {
        when(data){
            is StudentItemKt -> {
                itemView.research_item_header.text = data.headerTxt
                val adapter = UsefulLinksAdapterKt(callback)
                adapter.submitList(data.list)
                itemView.research_recycler.adapter = adapter
            }

            is ScheduleKt -> {}
        }
    }
}
