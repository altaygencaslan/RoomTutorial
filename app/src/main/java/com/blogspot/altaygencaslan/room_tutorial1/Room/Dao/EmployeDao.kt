package com.blogspot.altaygencaslan.room_tutorial1.Room.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.blogspot.altaygencaslan.room_tutorial1.Room.Entity.Employe

@Dao
interface EmployeDao {
    @Query("SELECT  * FROM tblemploye")
    fun getAllEmployes(): List<Employe>

    @Insert
    fun insertNewEmploye(employee: Employe)

    @Delete
    fun deleteEmployeByEntity(employee: Employe)

    @Query("DELETE  FROM tblemploye WHERE uid = :id")
    fun deleteEmployeById(id: Int)
}