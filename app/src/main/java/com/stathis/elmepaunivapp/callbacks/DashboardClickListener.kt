package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.home.fragments.dashboard.model.DashboardOption

interface DashboardClickListener {
    fun dashboardItemClicked(option: DashboardOption)
    fun learnMore()
}