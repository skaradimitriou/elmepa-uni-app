package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.CarouselParent
import com.stathis.elmepaunivapp.ui.main.students.model.NewStudentItem
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinksParent

class StudentAdapter(private val callback: ElmepaClickListener) : ListAdapter<LocalModel, StudentViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return StudentViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is NewStudentItem -> R.layout.holder_parent_horizontal_nested_item
        is UsefulLinksParent -> R.layout.holder_parent_vertical_grid_nested_item
        is CarouselParent -> R.layout.holder_viewpager_item
        else -> R.layout.holder_empty_view
    }
}