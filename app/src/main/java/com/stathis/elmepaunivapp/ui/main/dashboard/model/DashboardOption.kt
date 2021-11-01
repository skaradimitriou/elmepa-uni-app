package com.stathis.elmepaunivapp.ui.main.dashboard.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class DashboardOption(

    val title : String,
    val drawable : Int

) : LocalModel {
    override fun equalsContent(obj : LocalModel): Boolean = false
}
