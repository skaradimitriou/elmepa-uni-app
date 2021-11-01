package com.stathis.elmepaunivapp.ui.research.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.research.model.ResearchModel
import com.stathis.elmepaunivapp.ui.main.students.recycler.UsefulLinksAdapter
import kotlinx.android.synthetic.main.holder_research_item_row.view.*

class ResearchViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    val callback = callback

    override fun present(data: LocalModel) {
        when(data){
            is ResearchModel -> {
                itemView.research_item_header.text = data.categoryName

                val adapter = UsefulLinksAdapter(callback)
                adapter.submitList(data.researchItems)
                itemView.research_recycler.adapter = adapter
            }
        }
    }
}
