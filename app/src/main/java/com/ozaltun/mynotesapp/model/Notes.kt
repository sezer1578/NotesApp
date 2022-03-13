package com.ozaltun.mynotesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var title:String?,
    var notes:String?,
    var date:String?,
    var priority:String
) : Parcelable {
}


