package com.stathis.elmepaunivapp.ui.main.students.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class UsefulLinksParent(val title : String, val list : List<LocalModel>) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is UsefulLinksParent -> title == obj.title && list == obj.list
        else -> false
    }
}

