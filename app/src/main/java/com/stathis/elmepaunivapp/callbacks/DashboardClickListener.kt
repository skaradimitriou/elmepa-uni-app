package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.dashboard.model.DashboardOption

interface DashboardClickListener {
    fun dashboardItemClicked(option: DashboardOption)
}