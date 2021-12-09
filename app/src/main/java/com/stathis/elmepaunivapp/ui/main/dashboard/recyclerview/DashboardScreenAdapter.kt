package com.stathis.elmepaunivapp.ui.main.dashboard.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderDashboardOptionBinding

class DashboardScreenAdapter(private val callback: ElmepaClickListener) :
    ListAdapter<LocalModel, DashboardScreenViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardScreenViewHolder {
        val view = HolderDashboardOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardScreenViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DashboardScreenViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}