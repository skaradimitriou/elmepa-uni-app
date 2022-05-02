package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

class EmptyModel() : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}