package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class Research(val name : String ) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
