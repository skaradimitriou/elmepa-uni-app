package com.stathis.elmepaunivapp.model

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

    @ColumnInfo(name = "pubDate")
    val pubDate : String,

    @ColumnInfo(name = "imageResource")
    val imageResource : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean  = when(obj){
        is Announcement -> name == obj.name && url == obj.url && imageResource == obj.imageResource
        else -> false
    }
}
