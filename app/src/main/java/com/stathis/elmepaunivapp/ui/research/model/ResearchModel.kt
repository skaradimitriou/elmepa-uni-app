package com.stathis.elmepaunivapp.ui.research.model

import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks

data class ResearchModel(

    val categoryName : String,
    val researchItems : List<UsefulLinks>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is ResearchModel -> categoryName == obj.categoryName && researchItems == obj.researchItems
        else -> false
    }
}
