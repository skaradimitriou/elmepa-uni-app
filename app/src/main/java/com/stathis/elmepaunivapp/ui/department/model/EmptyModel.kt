package com.stathis.elmepaunivapp.ui.department.model

import com.stathis.elmepaunivapp.model.LocalModel

class EmptyModel() : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = false
}