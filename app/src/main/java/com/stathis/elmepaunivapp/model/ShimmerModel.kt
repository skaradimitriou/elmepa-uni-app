package com.stathis.elmepaunivapp.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

class ShimmerModel() : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}