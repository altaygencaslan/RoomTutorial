package com.blogspot.altaygencaslan.room_tutorial1.Room.Entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tblemploye", indices = arrayOf(Index(value = arrayOf("uid"), unique = true)))
data class Employe(var f: String, var t: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    var fullname: String = f
    var title: String = t
}