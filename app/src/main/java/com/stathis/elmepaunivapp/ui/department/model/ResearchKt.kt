package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class ResearchKt(val name : String ) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
