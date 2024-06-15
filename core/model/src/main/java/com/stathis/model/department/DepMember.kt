package com.stathis.model.department

import android.os.Parcelable
import com.stathis.model.UiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepMember(
    val image: String,
    val fullName: String,
    val profession: String,
    val description: String,
    val linkToResume: String,
    val skills: List<Skill>
) : UiModel, Parcelable {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DepMember -> image == obj.image && fullName == obj.fullName
        else -> false
    }
}

@Parcelize
data class Skill(
    val title: String,
    val value: Int
) : Parcelable