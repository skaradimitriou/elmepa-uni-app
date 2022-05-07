package com.stathis.elmepaunivapp.ui.research.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class ResearchResponse(

    val categoryName : String,
    val researchItems : List<ResearchItem>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is ResearchResponse -> categoryName == obj.categoryName && researchItems == obj.researchItems
        else -> false
    }
}
