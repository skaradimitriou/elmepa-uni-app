package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.LinkItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.SyllabusItem

class UsefulLinksAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, UsefulLinksViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsefulLinksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return UsefulLinksViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: UsefulLinksViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is SyllabusItem -> R.layout.useful_links_item_row
        is LinkItem -> R.layout.holder_horizontal_link_item
        else -> R.layout.holder_empty_view
    }
}
