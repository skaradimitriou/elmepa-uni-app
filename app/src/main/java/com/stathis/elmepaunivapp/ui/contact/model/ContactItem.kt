package com.stathis.elmepaunivapp.ui.contact.model

import com.stathis.elmepaunivapp.abstraction.LocalModel

data class ContactItem(
    val title : String,
    val description : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is ContactItem -> title == obj.title && description == obj.description
        else -> false
    }
}
