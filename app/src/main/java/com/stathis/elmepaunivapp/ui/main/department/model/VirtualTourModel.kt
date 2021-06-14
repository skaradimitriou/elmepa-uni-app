package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.model.LocalModel

data class VirtualTourModel(val name : String ) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
