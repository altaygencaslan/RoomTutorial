package com.blogspot.altaygencaslan.room_tutorial1.Room.DataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.blogspot.altaygencaslan.room_tutorial1.Room.Dao.EmployeDao
import com.blogspot.altaygencaslan.room_tutorial1.Room.Entity.Employe

@Database(entities = arrayOf(Employe::class), version = 3)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun employeDao(): EmployeDao

    //Static property ve method tanımlama bloğu
    companion object {
        @Volatile //Bu nedir?
        private var _dbContext: AppDatabase? = null

        fun getDbContext(context: Context): AppDatabase {
            val tempDbContext: AppDatabase? = _dbContext
            if (tempDbContext != null) {
                return tempDbContext
            }

            synchronized(this) {
                val instance: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "room_tutorial_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                _dbContext = instance
                return instance
            }
        }
    }
    //Static property ve method tanımlama bloğu
}