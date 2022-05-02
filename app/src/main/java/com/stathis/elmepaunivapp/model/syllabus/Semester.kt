package com.stathis.elmepaunivapp.model.syllabus

import android.os.Parcelable
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.lessons.model.Lesson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Semester(

    val semester: String,
    val lessonInfo: String,
    val lessons: List<Lesson>

) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Semester -> semester == obj.semester && lessonInfo == obj.lessonInfo && lessons == obj.lessons
        else -> false
    }
}