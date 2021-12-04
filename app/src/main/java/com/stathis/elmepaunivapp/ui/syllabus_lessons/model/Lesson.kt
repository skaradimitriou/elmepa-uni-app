package com.stathis.elmepaunivapp.ui.syllabus_lessons.model

import android.os.Parcelable
import com.stathis.elmepaunivapp.abstraction.LocalModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lesson(

    var name: String,
    val mandatory: String,
    val description: String,
    val direction: String

) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Lesson -> name == obj.name && mandatory == obj.mandatory && description == obj.description && direction == obj.direction
        else -> false
    }
}
