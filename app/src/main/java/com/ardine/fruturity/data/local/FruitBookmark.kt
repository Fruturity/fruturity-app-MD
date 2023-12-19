package com.ardine.fruturity.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "fruitBookmarked")
data class FruitBookmark(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "ripeness")
    val ripeness: String,

    @ColumnInfo(name = "category")
    val category: String ,

    @ColumnInfo(name = "fruitUrl")
    val fruitUrl: String? = null,

):Parcelable