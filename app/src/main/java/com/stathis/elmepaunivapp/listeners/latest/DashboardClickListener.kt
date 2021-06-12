package com.stathis.elmepaunivapp.listeners.latest

import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption

interface DashboardClickListener {

    fun dashboardItemClicked(option: DashboardOption)
    fun learnMore()
}