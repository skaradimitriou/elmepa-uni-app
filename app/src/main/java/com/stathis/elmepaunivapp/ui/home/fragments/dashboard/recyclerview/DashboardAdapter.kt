package com.stathis.elmepaunivapp.ui.home.fragments.dashboard.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderDashboardOptionBinding

class DashboardAdapter(private val callback: ElmepaClickListener) :
    ListAdapter<LocalModel, DashboardViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = HolderDashboardOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}