package com.stathis.elmepaunivapp.ui.lessons.model

import android.os.Parcelable
import com.stathis.elmepaunivapp.abstraction.LocalModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lesson(

    var name: String,
    val mandatory: Boolean,
    val description: String,
    var isExpanded: Boolean

) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Lesson -> name == obj.name && mandatory == obj.mandatory && description == obj.description
        else -> false
    }
}
