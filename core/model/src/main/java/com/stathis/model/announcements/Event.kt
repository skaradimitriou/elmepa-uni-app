package com.stathis.model.announcements

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stathis.model.UiModel
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Events")
data class Event(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    @NotNull
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "pubDate")
    val pubDate: String,

    @ColumnInfo(name = "imageResource")
    val imageResource: String

) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean {
        return when (obj) {
            is Event -> name == obj.name && url == obj.url && imageResource == obj.imageResource
            else -> false
        }
    }
}
