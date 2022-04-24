package com.stathis.elmepaunivapp.ui.home.dashboard.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class DashboardOption(

    val title : String,
    val drawable : Int

) : LocalModel {
    override fun equalsContent(obj : LocalModel): Boolean = when(obj){
        is DashboardOption -> title == obj.title && drawable == obj.drawable
        else -> false
    }
}
