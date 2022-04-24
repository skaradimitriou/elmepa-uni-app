package com.stathis.elmepaunivapp.ui.home.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

class EmptyModel() : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}