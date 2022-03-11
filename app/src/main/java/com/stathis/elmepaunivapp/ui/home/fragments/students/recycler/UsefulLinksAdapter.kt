package com.stathis.elmepaunivapp.ui.home.fragments.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderEmptyItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderHorizontalLinkItemBinding
import com.stathis.elmepaunivapp.databinding.HolderLinkItemRowBinding
import com.stathis.elmepaunivapp.databinding.UsefulLinksItemRowBinding
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.home.fragments.students.model.SyllabusItem

class UsefulLinksAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, UsefulLinksViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsefulLinksViewHolder {
        val view = when(viewType) {
            R.layout.holder_horizontal_link_item -> HolderHorizontalLinkItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.useful_links_item_row -> UsefulLinksItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_link_item_row -> HolderLinkItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return UsefulLinksViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: UsefulLinksViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is SyllabusItem -> R.layout.holder_link_item_row
        is LinkItem -> R.layout.holder_horizontal_link_item
        is UsefulLinks -> R.layout.useful_links_item_row
        else -> R.layout.holder_empty_view
    }
}
