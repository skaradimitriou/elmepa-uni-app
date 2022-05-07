package com.stathis.elmepaunivapp.ui.research.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class ResearchItem(
    val name: String,
    val url: String,
    val imageResource: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is ResearchItem -> name == obj.name && url == obj.url && imageResource == obj.imageResource
        else -> false
    }
}
