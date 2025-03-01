package com.elmepa.homeui.ui

import com.elmepa.homedomain.model.DashboardCard

internal class HomeView {

    sealed class State {
        object Loading : State()
        data class Content(val data: List<DashboardCard>) : State()
    }

    sealed interface UIAction {
        data class OptionTap(val option: DashboardCard) : UIAction
    }

    sealed interface Effect {
        data class OpenDashboardOption(val option: DashboardCard) : Effect
    }
}
