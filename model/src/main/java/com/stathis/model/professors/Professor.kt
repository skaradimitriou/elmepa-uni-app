package com.stathis.model.professors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stathis.core.base.UiModel
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Professors")
data class Professor(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "fullName")
    @NotNull
    val fullName: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "vocative")
    val vocative: String

) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Professor -> fullName == obj.fullName && email == obj.email && gender == obj.gender && vocative == obj.vocative
        else -> false
    }
}
