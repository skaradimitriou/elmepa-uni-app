package com.stathis.elmepaunivapp.ui.research.model

import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks

data class ResearchModel(

    val categoryName : String,
    val researchItems : List<UsefulLinks>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
