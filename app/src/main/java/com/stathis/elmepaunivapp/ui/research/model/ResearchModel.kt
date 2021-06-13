package com.stathis.elmepaunivapp.ui.research.model

import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt

data class ResearchModel(

    val categoryName : String,
    val researchItems : List<UsefulLinksKt>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
