package com.stathis.elmepaunivapp.ui.students.model

import android.widget.Button
import com.stathis.elmepaunivapp.model.LocalModel

data class ScheduleKt(

    val mainTxt : String,
    val mainButton : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
