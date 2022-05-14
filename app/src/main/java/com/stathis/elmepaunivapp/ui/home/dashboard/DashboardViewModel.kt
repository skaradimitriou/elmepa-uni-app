package com.stathis.elmepaunivapp.ui.home.dashboard

import android.app.Application
import android.view.View
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.home.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.home.dashboard.recyclerview.DashboardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = DashboardAdapter(this)
    private lateinit var callback: DashboardClickListener

    init {
        viewModelScope.launch(Dispatchers.IO){
            bindData()
        }
    }

    private fun bindData() {
        val list = listOf(
            DashboardOption(getString(R.string.dashboard_announcements), R.drawable.home_announcements),
            DashboardOption(getString(R.string.dashboard_department), R.drawable.home_department),
            DashboardOption(getString(R.string.dashboard_students), R.drawable.home_students),
            DashboardOption(getString(R.string.research_recycler_title), R.drawable.home_research)
        )
        adapter.submitList(list)
    }


    fun addCallback(callback: DashboardClickListener) {
        this.callback = callback
    }

    override fun onItemClick(view: View) = when (view.tag) {
       is DashboardOption -> callback.dashboardItemClicked(view.tag as DashboardOption)
       else -> Unit
    }
}