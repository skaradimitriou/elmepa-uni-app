package com.stathis.elmepaunivapp.ui.announcements.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stathis.elmepaunivapp.abstraction.LocalModel

@Entity(tableName = "Announcements")
data class Announcement(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    @NonNull
    val name : String,

    @ColumnInfo(name = "url")
    val url : String,

    @ColumnInfo(name = "imageResource")
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = false
}
