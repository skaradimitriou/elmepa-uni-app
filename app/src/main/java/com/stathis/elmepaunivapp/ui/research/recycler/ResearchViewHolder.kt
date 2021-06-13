package com.stathis.elmepaunivapp.ui.research.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.research.model.ResearchModel
import com.stathis.elmepaunivapp.ui.students.recycler.UsefulLinksAdapterKt
import kotlinx.android.synthetic.main.holder_research_item_row.view.*

class ResearchViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    val callback = callback

    override fun present(data: LocalModel) {
        when(data){
            is ResearchModel -> {
                itemView.research_item_header.text = data.categoryName

                val adapter = UsefulLinksAdapterKt(callback)
                adapter.submitList(data.researchItems)
                itemView.research_recycler.adapter = adapter
            }
        }
    }
}
