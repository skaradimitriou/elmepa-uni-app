package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption

interface DashboardClickListener {

    fun dashboardItemClicked(option: DashboardOption)
    fun learnMore()
}