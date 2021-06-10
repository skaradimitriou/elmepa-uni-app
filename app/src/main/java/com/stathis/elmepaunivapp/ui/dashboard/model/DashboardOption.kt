package com.stathis.elmepaunivapp.ui.dashboard.model

import com.stathis.elmepaunivapp.model.LocalModel

data class DashboardOption(

    val title : String,
    val drawable : Int

) : LocalModel {
    override fun equalsContent(obj : LocalModel): Boolean = false
}
